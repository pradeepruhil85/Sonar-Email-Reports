package com.jd.sonar.report.service.main;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jd.sonar.report.service.data.JDSonarService;
import com.jd.sonar.report.service.email.EmailCreator;
import com.jd.sonar.report.service.email.MailSender;
import com.jd.sonar.report.service.util.SonarUtils;
import com.sonar.report.service.bean.EmailParams;
import com.sonar.report.service.bean.ProjectStatsBean;

public class SonarClient {

	private static final Logger LOG = Logger.getLogger(SonarClient.class);
	
	private static ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {

		/*
		 * Get the argument for sending the sonar email weekly or every
		 * fortnight
		 */
		
		
		String targetAudience = null;
		
		int days = 0 ;
		if(args.length!=0){
			days = Integer.valueOf(args[0]);
			targetAudience = args[1];
		}
		
		if(targetAudience == null){
			targetAudience = "teamLeads";
		}
		if(days==0){
			/*Setting default to weekly */
			days = 7;
		}
		
		SonarClient client = new SonarClient();
		
		LOG.info("Sending the sonar email report, for the duration : " + days);
		
		Date today = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
		String date = dateFormat.format(today);
		Date prevDates = client.prevDate(today,days);
		String prevDate = dateFormat.format(prevDates);
		
		
		/* Get the sonar issues. */
		List<ProjectStatsBean> projectStatsBeans = client.getJDSonarReportData(prevDates);
		
		
		 
		
		/* Prepare the email body */
		EmailCreator creator = (EmailCreator) applicationContext.getBean("emailCreator");
		EmailParams emailParams = client.prepareEmailParams(projectStatsBeans,targetAudience);
		emailParams.setSubject("Sonar Summay Dashboard-" +date);
		emailParams.getParamsMapHolder().put("TODAYDATE", date);
		emailParams.getParamsMapHolder().put("PREVDATE", prevDate);
		
		emailParams = creator.createEmail(emailParams);
		
		/* send the email */
		 MailSender mailSender = (MailSender) applicationContext.getBean("springMailSender");
		 mailSender.sendMail(emailParams);
		
		 
	}
	
	private List<ProjectStatsBean> getJDSonarReportData(Date prevDate){
		
		JDSonarService jdSonarService = (JDSonarService) applicationContext.getBean("sonarJdService");
		
		List<ProjectStatsBean> projectStatsBeans = jdSonarService.getAllProjectsMetrics(SonarUtils.getSonarMetricsArray(),prevDate);

		LOG.debug("Projects Stats bean are : " + projectStatsBeans);
		return projectStatsBeans;
		
	}
	
	
	private EmailParams prepareEmailParams (List<ProjectStatsBean> projectStatsBeans, String targetCandidates){
		
		LOG.info("Enter prepareEmail paras " );
		EmailParams emailParams = new EmailParams();
		emailParams.setFrom("donot-reply@jd-sonarteam.com");
		emailParams.getParamsMapHolder().put("projectStatBeans", projectStatsBeans);
		if(targetCandidates.equalsIgnoreCase("teamLeads")){
			emailParams.setTos(SonarUtils.getSonarTeamLeadEmails().split(";"));
			emailParams.setCcs(SonarUtils.getSonarTeamLeadEmailsCC().split(";"));
		}else{
			emailParams.setTos(SonarUtils.getSonarDevelopmentTeamEmail().split(";"));
		}
		
		
//		emailParams.setTo("mgahlot@sapient.com");
//		emailParams.setTo("vtrehan@sapient.com");
		emailParams.setEmailTemplate("sonar-report.vm");
		return emailParams;
		
	}
	
	private Date prevDate(Date today, int day){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -day);
		Date dateBefore14Days = cal.getTime();
		
		return dateBefore14Days;
	}
	
}
