package com.lease.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo {
    /**
     *  
     */
    private Integer id;

    /**
     *  产品code
     */
    private String productCode;

    /**
     *  产品类型（1-集装箱 2-活动床 3-空调）
     */
    private Integer productType;

    /**
     *  购买时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date buyTime;

    /**
     *  制造商
     */
    private String factoryName;

    /**
     *  购买价格
     */
    private BigDecimal price;

    /**
     *  高
     */
    private BigDecimal high;

    /**
     *  宽
     */
    private BigDecimal with;

    /**
     *  长
     */
    private BigDecimal length;

    /**
     *  功率
     */
    private String power;

    /**
     *  状态（0-未出租 1-出租中）
     */
    private Integer status;

    /**
     *  
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getWith() {
        return with;
    }

    public void setWith(BigDecimal with) {
        this.with = with;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}