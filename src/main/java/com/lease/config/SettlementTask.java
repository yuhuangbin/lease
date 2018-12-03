package com.lease.config;

import com.lease.domain.LeaseInfo;
import com.lease.service.ILeaseInfoService;
import com.lease.sms.SmsService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-03
 */
@Component
public class SettlementTask {

    @Autowired
    private ILeaseInfoService leaseInfoService;

    @Autowired
    private SmsService smsService;

    @Value("${recive.email.account}")
    private String systemEmail;

    /**
     * 每天8点提醒结算
     */
//    @Scheduled(cron = "0 00 08 * * ?")
    @Scheduled(fixedRate = 10000)   //演示提醒邮件时可设置为10秒检查一次
    public void settlement() {
        AtomicInteger count = new AtomicInteger(0);
        List<LeaseInfo> leaseInfos = leaseInfoService.selectByStatus(0);
        leaseInfos.forEach(leaseInfo -> {
            boolean sameDay = DateUtils.isSameDay(leaseInfo.getStartDate(), leaseInfo.getEndDate());
            if (sameDay) {
                count.incrementAndGet();
                leaseInfo.setStatus(1);
                leaseInfoService.update(leaseInfo);
            }
        });

        if (count.get() != 0) {
            String content = String.format("您有%d笔订单已经到期，请及时查看处理", count.get());
            smsService.sendMail("租赁提醒",content,systemEmail);
        }
    }
}
