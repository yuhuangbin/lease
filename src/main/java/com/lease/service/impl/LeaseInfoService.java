package com.lease.service.impl;

import com.lease.api.ApiResponse;
import com.lease.domain.LeaseInfo;
import com.lease.domain.ProductInfo;
import com.lease.domain.User;
import com.lease.event.EmailEvent;
import com.lease.mapper.LeaseInfoMapper;
import com.lease.mapper.ProductInfoMapper;
import com.lease.page.Page;
import com.lease.service.ILeaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-02
 */
@Service
public class LeaseInfoService implements ILeaseInfoService {
    @Autowired
    private LeaseInfoMapper leaseInfoMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public ApiResponse leaseList(LeaseInfo leaseInfo) {
        List<LeaseInfo> leaseInfos = leaseInfoMapper.leaseList(leaseInfo);
        leaseInfos.forEach(item -> {
            calculate(item, new Date());
        });
        return ApiResponse.getInstance(leaseInfos);
    }

    @Override
    public LeaseInfo getById(Integer id) {
        return leaseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void del(Integer id) {
        leaseInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public ApiResponse save(LeaseInfo leaseInfo) {
        // 产品状态设置为出租中
        updateProductStatus(leaseInfo, 1);
        if (leaseInfo.getId() == null) {
            leaseInfo.setCreateDate(new Date());
            leaseInfo.setStatus(0);
            leaseInfoMapper.insertSelective(leaseInfo);
        } else  {
            leaseInfoMapper.updateByPrimaryKeySelective(leaseInfo);
        }
        return ApiResponse.SUCCESS;
    }

    @Override
    @Transactional
    public void settlement(Integer id, User userInfo) {
        LeaseInfo leaseInfo = leaseInfoMapper.selectByPrimaryKey(id);
        leaseInfo.setStatus(2);
        leaseInfoMapper.updateByPrimaryKeySelective(leaseInfo);
        // 产品状态设置回未出租
        updateProductStatus(leaseInfo, 0);
        calculate(leaseInfo, new Date());
        try {
            // 发送结算信息至邮箱
            sendEmail(leaseInfo,userInfo.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<LeaseInfo> selectByStatus(Integer status) {

        return leaseInfoMapper.selectByStatus(status);
    }

    @Override
    public void update(LeaseInfo leaseInfo) {
        leaseInfoMapper.updateByPrimaryKeySelective(leaseInfo);
    }

    @Override
    public ApiResponse leaseListpage(LeaseInfo leaseInfo, Page page) {
        List<LeaseInfo> leaseInfos = leaseInfoMapper.leaseListpage(leaseInfo,page);
        return ApiResponse.getInstance(leaseInfos);
    }

    private void sendEmail(LeaseInfo leaseInfo, String userAccount) {
        String content = buildContent(leaseInfo);
        publisher.publishEvent(new EmailEvent("结算信息", content, userAccount));
    }

    private String buildContent(LeaseInfo leaseInfo) {
        Date now = new Date();
        int day = getDay(leaseInfo.getStartDate(), now);
        StringBuilder content = new StringBuilder();
        content.append("您好!").append("<br>")
                     .append("您有一笔租赁订单已结算，信息如下：").append("<br>")
                     .append("    租赁日期:").append(DateFormatUtils.format(leaseInfo.getStartDate(),"yyyy-MM-dd")).append("至")
                     .append(DateFormatUtils.format(new Date(),"yyyy-MM-dd")).append("<br>");

        if (StringUtils.isNotEmpty(leaseInfo.getBoxCode())) {
            content.append("集装箱费用：").append(new BigDecimal(day * 6)).append("元<br>");
        }
        if (StringUtils.isNotEmpty(leaseInfo.getBedCode())) {
            content.append("活动床费用：").append(new BigDecimal(day * 1)).append("元<br>");
        }
        if (StringUtils.isNotEmpty(leaseInfo.getAirCode())) {
            content.append("空调费用：").append(new BigDecimal(day * 2)).append("元<br>");
        }
        content.append("共计："+ leaseInfo.getCurrentCost() + "元");
        return content.toString();
    }

    private void updateProductStatus(LeaseInfo leaseInfo,Integer status) {
        if (StringUtils.isNotEmpty(leaseInfo.getBoxCode())) {
            doUpdate(leaseInfo.getBoxCode(),status);
        }
        if (StringUtils.isNotEmpty(leaseInfo.getBedCode())) {
            doUpdate(leaseInfo.getBedCode(),status);
        }
        if (StringUtils.isNotEmpty(leaseInfo.getAirCode())) {
            doUpdate(leaseInfo.getAirCode(),status);
        }
    }

    private void doUpdate(String code,Integer status) {
        ProductInfo record = productInfoMapper.selectByCode(code);
        record.setStatus(status);
        productInfoMapper.updateByPrimaryKeySelective(record);
    }

    private void calculate(LeaseInfo item, Date endDate) {
        BigDecimal sum = BigDecimal.valueOf(0);
        int day = getDay(item.getStartDate(),endDate);
        if (StringUtils.isNotEmpty(item.getBoxCode())) {
            sum = sum.add(new BigDecimal(day * 6));
        }
        if (StringUtils.isNotEmpty(item.getAirCode())) {
            sum = sum.add(new BigDecimal(day * 2));
        }
        if (StringUtils.isNotEmpty(item.getBedCode())) {
            sum = sum.add(new BigDecimal(day * 1));
        }
        item.setCurrentCost(sum);
    }

    private int getDay(Date startDate, Date endDate) {
        long between = (endDate.getTime() - startDate.getTime()) / 1000;
        int day = (int) (between/ (24 * 3600));
        return day;
    }



}
