package com.lease.config;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
public enum PageViewEnum {
    index("/index","index"),
    buyList("/sys/buyList","/sys/buyList"),
    leaseList("/sys/leaseList","/sys/leaseList"),
    ;


    public String url;

    public String view;

    PageViewEnum (String url, String view) {
        this.url = url;
        this.view = view;
    }
}
