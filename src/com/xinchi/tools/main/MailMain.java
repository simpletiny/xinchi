package com.xinchi.tools.main;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class MailMain {
	public static void main(String[] args) throws MessagingException {
		// 这个类主要是设置邮件
		String serverHost = "smtp.163.com";
		String serverPort = "25";
		String sendUser = "simpletiny@163.com";
		String sendPassword = "@141592st";
		String subject = "test1 mail";
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(serverHost);
		mailInfo.setMailServerPort(serverPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(sendUser);
		String pwd = new String(sendPassword);
		mailInfo.setPassword(pwd);// 您的邮箱密码
		mailInfo.setFromAddress(sendUser);
		mailInfo.setToAddress("290760943@qq.com");

		mailInfo.setSubject(subject);
		// 设置附件

		MimeBodyPart attachmentPart = new MimeBodyPart();

		FileDataSource source = new FileDataSource(new File(
				"D:\\books\\通往奴役之路.CHM"));
		attachmentPart.setDataHandler(new DataHandler(source));
		attachmentPart.setFileName("bb.CHM");
		
		mailInfo.setContent("");
		mailInfo.setAttachmentPart(attachmentPart);
		// mailInfo.setContent(html);
		// 这个类主要来发送邮件
		MailSender sms = new MailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		sms.sendMail(mailInfo);// 发送html格式
	}
}
