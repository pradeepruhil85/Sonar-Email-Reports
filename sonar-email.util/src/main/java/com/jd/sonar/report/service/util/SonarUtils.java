package com.jd.sonar.report.service.util;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class SonarUtils {

	private static final Logger LOG = Logger.getLogger(SonarUtils.class);

	public static String getSonarHost() {

		LOG.info("Enter getSonarhost from sonar.properties file");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");

		String host = bundle.getString("sonar.host");

		LOG.info("Sonar host is " + host);

		return host;
	}

	public static String getSonarPort() {
		LOG.info("Enter getSonarPort method, fetch the port for sonar server from sonar.properties file");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String port = bundle.getString("sonar.port");

		LOG.info("sonar port is " + port);
		return port;
	}

	public static String getSonarServerUserName() {
		LOG.info("Enter getSonarServerUserName method, fetch the username for sonar server from sonar.properties file");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String userName = bundle.getString("sonar.username");

		LOG.info("sonar useranem is " + userName);
		return userName;
	}

	public static String getSonarServerPassword() {
		LOG.info("Enter getSonarServerPassword method, fetch the username for sonar server from sonar.properties file");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String password = bundle.getString("sonar.password");

		LOG.info("sonar password is " + password);
		return password;
	}

	public static String[] getSonarMetricsArray() {
		LOG.info("Enter getSonarMetricsArray ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String metricString = bundle.getString("sonar.project.metrics");

		String[] metrics = metricString.split(",");

		LOG.info("sonar metric is " + metrics);
		return metrics;

	}

	public static List<String> getExcludedProjectList() {
		LOG.info("Enter getExcludedProjectList ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String metricString = bundle.getString("sonar.exclude.projects");

		String[] excludeProjects = metricString.split(",");

		LOG.info("sonar metric is " + excludeProjects);
		return Arrays.asList(excludeProjects);

	}

	public static String getSonarProjectUrl(String projectName) {
		LOG.info("Enter getSonarProjectUrl ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String projectUrlr = bundle.getString("sonar.".concat(projectName));
		LOG.info("sonar projectUrlr is " + projectUrlr);
		return projectUrlr;

	}

	public static String getSonarTeamLeadEmails() {
		LOG.info("Enter getSonarTeamLeadEmails ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String teamLeads = bundle.getString("sonar.to.teamleademails");
		LOG.info("sonar projectUrlr is " + teamLeads);
		return teamLeads;
	}
	
	public static String getSonarTeamLeadEmailsCC() {
		LOG.info("Enter getSonarTeamLeadEmails ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String teamLeads = bundle.getString("sonar.cc.teamleademails");
		LOG.info("sonar projectUrlr is " + teamLeads);
		return teamLeads;
	}

	public static String getSonarDevelopmentTeamEmail() {
		LOG.info("Enter getSonarTeamLeadEmails ");

		ResourceBundle bundle = ResourceBundle.getBundle("sonar");
		String development = bundle.getString("sonar.to.developmentTeam");
		LOG.info("sonar development is " + development);
		return development;		
	}
}
