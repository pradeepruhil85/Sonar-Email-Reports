package com.sonar.report.service.bean;

/**
 * <code>EmailParams</code> class holds the email parameters to be used to
 * construct the emails.
 * 
 * @author pruhil
 * 
 */
public class EmailParams extends CommunicationParam {

	/** Email template name to be used to generate the email body */
	private String emailTemplate;

	/** To information */
	private String to;

	/** Multiple reciepents information */
	private String[] tos;

	private String[] ccs;

	/** email from */
	private String from;

	/** text information */
	private String text;

	/** subject information */
	private String subject;

	/** emailBody */
	private String emailBody;

	/**
	 * @return the emailTemplate
	 */
	public String getEmailTemplate() {
		return emailTemplate;
	}

	/**
	 * @param emailTemplate
	 *            the emailTemplate to set
	 */
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param emailBody
	 *            The emailBody to set.
	 */
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	/**
	 * @return Returns the emailBody.
	 */
	public String getEmailBody() {
		return emailBody;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	public void setTos(String[] tos) {
		this.tos = tos;
	}

	public String[] getTos() {
		return tos;
	}

	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}

	public String[] getCcs() {
		return ccs;
	}

}
