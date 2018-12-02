package com.lease.service.impl;

import com.lease.mapper.LeaseInfoMapper;
import com.lease.service.ILeaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-02
 */
@Service
public class LeaseInfoService implements ILeaseInfoService {
    @Autowired
    private LeaseInfoMapper leaseInfoMapper;

}
