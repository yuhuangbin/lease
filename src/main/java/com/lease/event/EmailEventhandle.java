package com.lease.event;

import com.lease.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-03
 */
@Component
public class EmailEventhandle {
    @Autowired
    private SmsService smsService;

    @EventListener
    @Async
    public void sendEmail(EmailEvent emailEvent) {
        smsService.sendMail(emailEvent.getSubject(),emailEvent.getContent(),emailEvent.getTo());
    }
}
