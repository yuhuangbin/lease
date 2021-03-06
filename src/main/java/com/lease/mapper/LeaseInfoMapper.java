package com.lease.mapper;

import com.lease.domain.LeaseInfo;
import com.lease.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaseInfo record);

    int insertSelective(LeaseInfo record);

    LeaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaseInfo record);

    int updateByPrimaryKey(LeaseInfo record);

    List<LeaseInfo> leaseList(LeaseInfo leaseInfo);

    List<LeaseInfo> selectByStatus(Integer status);

    List<LeaseInfo> leaseListpage(@Param("leaseInfo") LeaseInfo leaseInfo, @Param("page")Page page);
}