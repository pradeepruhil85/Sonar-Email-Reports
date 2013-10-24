package com.sonar.report.service.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;
import org.sonar.wsclient.services.TimeMachine;
import org.sonar.wsclient.services.TimeMachineCell;
import org.sonar.wsclient.services.TimeMachineQuery;

import com.sonar.report.service.bean.ProjectStatsBean;
import com.sonar.report.service.util.SonarUtils;

public class SonarServiceImpl implements SonarService {

	private static Logger LOG = Logger.getLogger(SonarServiceImpl.class);

	@Override
	public List<ProjectStatsBean> getAllProjectsMetrics(String[] metrics, Date prevDate) {

		LOG.info("Enter getAppProjects to get all the projects by calling the sonar client ");

		Sonar sonar = Sonar.create(getSonarServerUrl(),
				SonarUtils.getSonarServerUserName(),
				SonarUtils.getSonarServerPassword());

		List<Resource> resources = sonar.findAll(new ResourceQuery()
				.setMetrics(metrics));

		if (resources != null) {
			LOG.debug("list of resouces returned are " + resources.size());
		}
		LOG.debug("Resources returned are " + resources);
		return populateSonarServiceResponse(resources,prevDate,metrics);
	}

	private List<ProjectStatsBean> populateSonarServiceResponse(List<Resource> resources, Date prevDate,String[] metrics){
		LOG.info("Resource returned are " + resources);
		
		List<ProjectStatsBean> projectStatsBeans = new ArrayList<ProjectStatsBean>();
		List<String> excludedProjectList = SonarUtils.getExcludedProjectList();
		
		/* Iterate throught the resources */
		for(Resource resource : resources){

			LOG.debug("Parsing resource " + resource.getName());
			if(excludedProjectList.contains(resource.getName())){
				continue;
			}
			ProjectStatsBean projectStatsBean = new ProjectStatsBean();
			projectStatsBean.setProjectName(resource.getName());
			projectStatsBean.setLinesOfConde(resource.getMeasureFormattedValue("ncloc", "0"));
			projectStatsBean.setRulesCompliance(resource.getMeasureValue("violations_density"));
			projectStatsBean.setRulesComplianceFomatted(resource.getMeasureFormattedValue("violations_density", "0.00"));
			projectStatsBean.setBlocker(resource.getMeasureValue("blocker_violations"));
			projectStatsBean.setCritical(resource.getMeasureValue("critical_violations"));
			projectStatsBean.setMajor(resource.getMeasureValue("major_violations"));
			projectStatsBean.setMinor(resource.getMeasureValue("minor_violations"));
			projectStatsBean.setInfo(resource.getMeasureValue("info_violations"));
			
			getPrevProjectDetails(projectStatsBean, resource.getKey(), prevDate, metrics);
			projectStatsBeans.add(projectStatsBean);
		}
		LOG.info("PRojects stats beans list " + projectStatsBeans);
		return projectStatsBeans;
	}
	
	/**
	 * getSonarServerUrl method will return the sonar ULR
	 * 
	 * @return
	 */
	private String getSonarServerUrl() {

		LOG.info("Enter get Sonar server URl, to get the sonar server URL");

		String host = SonarUtils.getSonarHost();
		String port = SonarUtils.getSonarPort();

		LOG.debug("Sonar host  and Port are " + host + " " + port);

		String sonarUrl = new StringBuilder("http://").append(host).append(":")
				.append(port).toString();

		LOG.info("Exit getSonarUrl, sonar Url " + sonarUrl);

		return sonarUrl;
	}

	private void getPrevProjectDetails(ProjectStatsBean projectStatsBean, String resourceId,
			Date prevDate, String[] metrics) {
		Sonar sonar = Sonar.create(getSonarServerUrl(),
				SonarUtils.getSonarServerUserName(),
				SonarUtils.getSonarServerPassword());

		List<TimeMachine> resources = sonar.findAll(new TimeMachineQuery(resourceId)
				.setMetrics(metrics));
		boolean gotData =false;
		TimeMachineCell lastPopulatedTimeMachineCell = null;
		for(TimeMachine machine : resources){
			
			TimeMachineCell[] columnCelss = machine.getCells();
			
			for(TimeMachineCell machineCell : columnCelss){
				Date date = machineCell.getDate();
				lastPopulatedTimeMachineCell = machineCell;
				if(date.getDate()== prevDate.getDate() && date.getMonth() == prevDate.getMonth() && date.getDay() == prevDate.getDay()){
					Object[] values = machineCell.getValues();
					projectStatsBean.setPrevLinesOfCode(String.valueOf((Long)(values[0])) );
					projectStatsBean.setPrevRulesCompliance((Double) values[1]);
					projectStatsBean.setPrevBlocker(Double.valueOf((Long) values[2]));
					projectStatsBean.setPrevCritical(Double.valueOf((Long)values[3]));
					projectStatsBean.setPrevMajor(Double.valueOf((Long) values[4]));
					projectStatsBean.setPrevMinor(Double.valueOf((Long) values[5]));
					projectStatsBean.setPrevInfo(Double.valueOf((Long) values[6]));
					gotData = true;
					return;
				}
			}
		}
		if(!gotData){
			Object[] values = lastPopulatedTimeMachineCell.getValues();
			projectStatsBean.setPrevLinesOfCode(String.valueOf((Long)(values[0])) );
			projectStatsBean.setPrevRulesCompliance((Double) values[1]);
			projectStatsBean.setPrevBlocker(Double.valueOf((Long) values[2]));
			projectStatsBean.setPrevCritical(Double.valueOf((Long)values[3]));
			projectStatsBean.setPrevMajor(Double.valueOf((Long) values[4]));
			projectStatsBean.setPrevMinor(Double.valueOf((Long) values[5]));
			projectStatsBean.setPrevInfo(Double.valueOf((Long) values[6]));
		}
		
	}
}
