package com.cndym.email;


import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.cndym.util.ConfigUtils;
import com.cndym.util.SpringUtils;

public class MailEngine {
	
	private final Log log = LogFactory.getLog(MailEngine.class);
	private MailSender mailSender;
	private VelocityEngine velocityEngine;
	private String defaultFrom;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setFrom(String from) {
		defaultFrom = from;
	}

	public void sendMessage(SimpleMailMessage msg, String templateName,
			Map model) {
		String result = null;
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, templateName, model);
		} catch (VelocityException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		msg.setText(result);
		send(msg);
	}

	public void send(SimpleMailMessage msg) throws MailException {
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			log.error(ex.getCause().getMessage());
			throw ex;
		}
	}

	public void sendMessage(String[] recipients, String sender,
			ClassPathResource resource, String bodyText, String subject,
			String attachmentName) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "GBK");

		helper.setTo(recipients);

		if (sender == null) {
			helper.setFrom(defaultFrom);
		} else {
			helper.setFrom(sender);
		}

		helper.setText(bodyText, true);
		helper.setSubject(subject);
		if (resource != null) {
			helper.addAttachment(attachmentName, resource);
		}
		((JavaMailSenderImpl) mailSender).send(message);
	}

}

