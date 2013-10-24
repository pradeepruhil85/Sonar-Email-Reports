package com.sonar.report.service.bean;

import java.util.Date;

/**
 * 
 * @author Pradeep
 * 
 */
public class ProjectStatsBean {

	private String projectName;

	private String linesOfConde;

	private Double rulesCompliance;

	private String rulesComplianceFomatted;

	private Double blocker;

	private Double critical;

	private Double major;

	private Double minor;

	private Double info;

	private Date currentData;

	private Double prevRulesCompliance;

	private String prevLinesOfCode;

	private Double prevBlocker;

	private Double prevCritical;

	private Double prevMajor;

	private Double prevMinor;

	private Double prevInfo;
	
	private int differentialBlocker;
	
	private int differentialCritical;
	
	private int differentialMajor;
	
	private int differentialMinor;
	
	private int differentialInfo;
	
	public void setRulesComplianceFomatted(String rulesComplianceFomatted) {
		this.rulesComplianceFomatted = rulesComplianceFomatted;
	}

	public String getRulesComplianceFomatted() {
		return rulesComplianceFomatted;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLinesOfConde() {
		return linesOfConde;
	}

	public void setLinesOfConde(String linesOfConde) {
		this.linesOfConde = linesOfConde;
	}

	public Double getRulesCompliance() {
		return rulesCompliance;
	}

	public void setRulesCompliance(Double rulesCompliance) {
		this.rulesCompliance = rulesCompliance;
	}

	public Double getBlocker() {
		return blocker;
	}

	public void setBlocker(Double blocker) {
		this.blocker = blocker;
	}

	public Double getCritical() {
		return critical;
	}

	public void setCritical(Double critical) {
		this.critical = critical;
	}

	public Double getMajor() {
		return major;
	}

	public void setMajor(Double major) {
		this.major = major;
	}

	public Double getMinor() {
		return minor;
	}

	public void setMinor(Double minor) {
		this.minor = minor;
	}

	public Double getInfo() {
		return info;
	}

	public void setInfo(Double info) {
		this.info = info;
	}

	public Date getCurrentData() {
		return currentData;
	}

	public void setCurrentData(Date currentData) {
		this.currentData = currentData;
	}

	public Double getPrevRulesCompliance() {
		return prevRulesCompliance;
	}

	public void setPrevRulesCompliance(Double prevRulesCompliance) {
		this.prevRulesCompliance = prevRulesCompliance;
	}

	public String getPrevLinesOfCode() {
		return prevLinesOfCode;
	}

	public void setPrevLinesOfCode(String prevLinesOfCode) {
		this.prevLinesOfCode = prevLinesOfCode;
	}

	public Double getPrevBlocker() {
		return prevBlocker;
	}

	public void setPrevBlocker(Double prevBlocker) {
		this.prevBlocker = prevBlocker;
	}

	public Double getPrevCritical() {
		return prevCritical;
	}

	public void setPrevCritical(Double prevCritical) {
		this.prevCritical = prevCritical;
	}

	public Double getPrevMajor() {
		return prevMajor;
	}

	public void setPrevMajor(Double prevMajor) {
		this.prevMajor = prevMajor;
	}

	public Double getPrevMinor() {
		return prevMinor;
	}

	public void setPrevMinor(Double prevMinor) {
		this.prevMinor = prevMinor;
	}

	public Double getPrevInfo() {
		return prevInfo;
	}

	public void setPrevInfo(Double prevInfo) {
		this.prevInfo = prevInfo;
	}
	
	public int getDifferentialBlocker() {
		differentialBlocker = this.blocker.intValue() - this.prevBlocker.intValue();
		return differentialBlocker;
	}
	
	public int getDifferentialCritical() {
		differentialCritical = this.critical.intValue() - this.prevCritical.intValue();
		return differentialCritical;
	}
	
	public int getDifferentialInfo() {
		this.differentialInfo = this.info.intValue() - this.prevInfo.intValue();
		return differentialInfo;
	}
	
	public int getDifferentialMajor() {
		
		this.differentialMajor = this.major.intValue() - this.prevMajor.intValue();
		return differentialMajor;
	}
	
	public int getDifferentialMinor() {
		this.differentialMinor = this.minor.intValue() - this.prevMinor.intValue();
		return differentialMinor;
	}
	

	@Override
	public String toString() {
		return "ProjectStatsBean [projectName=" + projectName
				+ ", linesOfConde=" + linesOfConde + ", rulesCompliance="
				+ rulesCompliance + ", rulesComplianceFomatted="
				+ rulesComplianceFomatted + ", blocker=" + blocker
				+ ", critical=" + critical + ", major=" + major + ", minor="
				+ minor + ", info=" + info + ", currentData=" + currentData
				+ ", prevRulesCompliance=" + prevRulesCompliance
				+ ", prevLinesOfCode=" + prevLinesOfCode + ", prevBlocker="
				+ prevBlocker + ", prevCritical=" + prevCritical
				+ ", prevMajor=" + prevMajor + ", prevMinor=" + prevMinor
				+ ", prevInfo=" + prevInfo + "]";
	}

}
