package com.lease.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class LeaseInfo {
    /**
     *  
     */
    private Integer id;

    /**
     *  集装箱编码
     */
    private String boxCode;

    /**
     *  空调编码
     */
    private String airCode;

    /**
     *  活动床编码
     */
    private String bedCode;

    /**
     *  承租人姓名
     */
    private String lesseeName;

    /**
     *  电话
     */
    private String lesseeTel;

    /**
     *  所属公司
     */
    private String lesseeCompany;

    /**
     *  押金
     */
    private BigDecimal cashPledge;

    /**
     *  0-出租中 1-已到期 2-已结算
     */
    private Integer status;
    private String statusName;

    /**
     *  开始时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    /**
     *  结束时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    /**
     *  
     */
    private Date createDate;

    private BigDecimal currentCost;

    public String getStatusName() {
        if (this.status == 0) {
            return "出租中";
        } else if (this.status == 1){
            return "已到期";
        } else if (this.status == 2){
            return "已结算";
        }
        return "";
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode == null ? null : boxCode.trim();
    }

    public String getAirCode() {
        return airCode;
    }

    public void setAirCode(String airCode) {
        this.airCode = airCode == null ? null : airCode.trim();
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode == null ? null : bedCode.trim();
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName == null ? null : lesseeName.trim();
    }

    public String getLesseeTel() {
        return lesseeTel;
    }

    public void setLesseeTel(String lesseeTel) {
        this.lesseeTel = lesseeTel == null ? null : lesseeTel.trim();
    }

    public String getLesseeCompany() {
        return lesseeCompany;
    }

    public void setLesseeCompany(String lesseeCompany) {
        this.lesseeCompany = lesseeCompany == null ? null : lesseeCompany.trim();
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}