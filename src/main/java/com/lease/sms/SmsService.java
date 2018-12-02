package com.lease.sms;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SmsService {
    private static String smtp_host = "smtp.163.com"; // 网易
    private static String password = "admin111111"; // 邮箱授权码
    private static String from = "mexiaojianbang@163.com"; // 使用当前账户

    public static void sendMail(String subject, String content, String to) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp_host);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from,"租赁到期提醒"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            message.setSentDate(new Date());
            Transport transport = session.getTransport();
            transport.connect(smtp_host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }

    public static void main(String[] args) {
        sendMail("通知","您的日报","943695742@qq.com");
    }
}
