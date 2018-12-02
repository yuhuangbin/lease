package com.lease.controller;

import com.lease.service.ILeaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-02
 */
@RestController
@RequestMapping("/lease")
public class LeaseController {

    @Autowired
    private ILeaseInfoService leaseInfoService;


}
