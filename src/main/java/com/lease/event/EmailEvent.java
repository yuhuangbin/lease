package com.lease.event;

import org.springframework.context.ApplicationEvent;

/**
 * Description:发送邮件事件
 * author: yu.hb
 * Date: 2018-12-03
 */
public class EmailEvent extends ApplicationEvent {
    private String subject;
    private String content;
    private String to;

    public EmailEvent(String subject, String content, String to) {
        super(new Object());
        this.subject = subject;
        this.content = content;
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
