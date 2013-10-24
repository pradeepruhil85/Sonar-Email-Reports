package com.sonar.report.service.bean;

import java.util.HashMap;
import java.util.Map;

public class CommunicationParam {

	/** Map containing the parameters map which will be useful in velocity email */
	private Map<String, Object> paramsMapHolder = new HashMap<String, Object>();
	
	/** @param paramsMapHolder The paramsMapHolder to set.
	 */
	public void setParamsMapHolder(Map<String, Object> paramsMapHolder) {
		this.paramsMapHolder = paramsMapHolder;
	}
	
	/** @return Returns the paramsMapHolder.
	 */
	public Map<String, Object> getParamsMapHolder() {
		return paramsMapHolder;
	}
	
}
