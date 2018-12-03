package com.lease.service;

import com.lease.api.ApiResponse;
import com.lease.domain.LeaseInfo;
import com.lease.domain.User;

import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-02
 */
public interface ILeaseInfoService {
    ApiResponse leaseList(LeaseInfo leaseInfo);

    LeaseInfo getById(Integer id);

    void del(Integer id);

    ApiResponse save(LeaseInfo leaseInfo);

    void settlement(Integer id,User userInfo);

    List<LeaseInfo> selectByStatus(Integer status);

    void update(LeaseInfo leaseInfo);
}
