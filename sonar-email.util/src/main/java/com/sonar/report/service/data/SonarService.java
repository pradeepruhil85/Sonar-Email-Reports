package com.sonar.report.service.data;

import java.util.Date;
import java.util.List;

import com.sonar.report.service.bean.ProjectStatsBean;

/**
 * 
 * Sonar Service interface contains method to fetch the data from Sonar using
 * the SOnar client
 * 
 * @author Pradeep
 * 
 */
public interface SonarService {
	

	/**
	 * Returns all the projects configured in Sonar
	 * 
	 * @return
	 */
	List<ProjectStatsBean> getAllProjectsMetrics(String [] metrics, Date prevDate);
	
}
