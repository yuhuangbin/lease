package com.lease.controller;

import com.lease.api.ApiResponse;
import com.lease.domain.LeaseInfo;
import com.lease.domain.ProductInfo;
import com.lease.domain.User;
import com.lease.page.Page;
import com.lease.service.ILeaseInfoService;
import com.lease.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-02
 */
@RestController
@RequestMapping("/api/lease")
public class LeaseController {

    @Autowired
    private ILeaseInfoService leaseInfoService;

    @RequestMapping("/list")
    public ApiResponse leaseList(@RequestBody LeaseInfo leaseInfo) {
        return leaseInfoService.leaseList(leaseInfo);
    }

    @RequestMapping("/listPage")
    public ApiResponse leasePage(LeaseInfo leaseInfo, Page page) {
        return leaseInfoService.leaseListpage(leaseInfo,page);
    }

    @RequestMapping("/del")
    public ApiResponse leaseList(Integer id) {
        leaseInfoService.del(id);
        return ApiResponse.SUCCESS;
    }

    @RequestMapping("/getByid")
    public ApiResponse getByid(Integer id) {
        return ApiResponse.getInstance(leaseInfoService.getById(id));
    }

    @RequestMapping("/settlement")
    public ApiResponse del(Integer id, HttpSession session) {
        User userInfo = (User) session.getAttribute("userInfo");
        leaseInfoService.settlement(id, userInfo);
        return ApiResponse.SUCCESS;
    }
    @RequestMapping("/save")
    public ApiResponse save(@RequestBody LeaseInfo leaseInfo) {
        return leaseInfoService.save(leaseInfo);
    }
}
