package com.jd.sonar.report.service.email;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sonar.report.service.bean.EmailParams;

public class VelocityEmailCreator implements EmailCreator {

	private static final Logger LOG = Logger
			.getLogger(VelocityEmailCreator.class);

	/** Velocity engine to make up the markup */
	private VelocityEngine velocityEngine;

	/**
	 * @see com.johndeere.myjd.service.email.CreateEmail#createEmail(com.johndeere.myjd.service.email.model.EmailParams)
	 * @param emailParams
	 *            Email params required for creating the email
	 * @return the Email content
	 */
	@Override
	public EmailParams createEmail(EmailParams emailParams)
			{
		if (LOG.isInfoEnabled()) {
			LOG.info("Enter create Email");
		}
		// Get the template Variables to be used */
		Map<String, Object> hTemplateVariables = emailParams
				.getParamsMapHolder();
		String emailBody = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, emailParams.getEmailTemplate(),"UTF-8",
				hTemplateVariables);

		if(LOG.isDebugEnabled()){
			LOG.debug("Email body returned is : " + emailBody );
		}
		emailParams.setEmailBody(emailBody);
		
		if(LOG.isDebugEnabled()){
		    LOG.debug("Exit Create Email " + emailParams);
		}
		return emailParams;
	}
	
	/** @param velocityEngine The velocityEngine to set.
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    
    /** @return Returns the velocityEngine.
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

}
