package com.sonar.report.service.email;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.sonar.report.service.bean.EmailParams;

public class SpringMailSender implements MailSender {

	private static final Logger LOG = Logger.getLogger(SpringMailSender.class);
	/** Injected the spring mail sender */
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(final EmailParams emailParams) {

		 if (LOG.isDebugEnabled()) {
				LOG.debug("Inside SpringMailSender, send mail " + emailParams.getEmailBody());
			}
			
			/* Preparing the message mime body */
			MimeMessagePreparator preparator;
			try {
				preparator = new MimeMessagePreparator() {
					public void prepare(MimeMessage mimeMessage) throws Exception {
					    mimeMessage.setContent(emailParams.getEmailBody(), "text/html; charset=utf-8");
						MimeMessageHelper message = new MimeMessageHelper(
								mimeMessage);
						message.setTo(emailParams.getTos());
						message.setFrom(emailParams.getFrom());
						if(emailParams.getCcs()!=null && emailParams.getCcs().length!=0){
							message.setCc(emailParams.getCcs());
						}
						message.setSubject(emailParams.getSubject());
						message.setText(emailParams.getEmailBody(), true);
						
					}
				};
			} catch (Exception e) {
				LOG
						.error("Error occurred while preparing the mime message for the mail", e);
				throw new RuntimeException();
			}

			/* send the mail */
			try {
				mailSender.send(preparator);
			} catch (MailException mailException) {
				LOG.error("Exception occurred while sending the mail ",
						mailException);
				throw new RuntimeException();
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug("End sending the mail");
			}
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

}
