package com.jd.sonar.report.service.email;

import com.sonar.report.service.bean.EmailParams;

public interface EmailCreator {
	/**
	 * CreateEmail method will create the email template
	 * 
	 * @param emailParams
	 * @return
	 */
	EmailParams createEmail(EmailParams emailParams);

}
