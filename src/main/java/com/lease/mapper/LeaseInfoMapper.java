package com.lease.mapper;

import com.lease.domain.LeaseInfo;

public interface LeaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaseInfo record);

    int insertSelective(LeaseInfo record);

    LeaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaseInfo record);

    int updateByPrimaryKey(LeaseInfo record);
}