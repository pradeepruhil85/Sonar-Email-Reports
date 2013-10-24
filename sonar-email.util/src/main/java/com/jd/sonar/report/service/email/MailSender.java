package com.jd.sonar.report.service.email;

import com.sonar.report.service.bean.EmailParams;

/**
 * MailSender interface will provide functionality for sending the mails. All
 * the necessary parameters that will be required for sending the mails will be
 * passed in the emails params class.
 * 
 * @author pruhil
 * @version $Revision$
 */
public interface MailSender {

	/**
	 * <code>sendMail</code> method will provide the email functionality for
	 * sending the mails.
	 * 
	 * @param emailParams
	 *            input parameters for sending the mails
	 * @return CommunicationResponse
	 */
	public void sendMail(EmailParams emailParams);
}