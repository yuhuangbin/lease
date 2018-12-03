package com.lease.service;

import com.lease.api.ApiResponse;
import com.lease.domain.LeaseInfo;
import com.lease.domain.ProductInfo;

import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-01
 */
public interface IProductService {
    List<ProductInfo> selectAll();

    ApiResponse save(ProductInfo productInfo);

    List<ProductInfo> selectList(ProductInfo productInfo);

    ApiResponse del(Integer id);

    ProductInfo getByid(Integer id);

    ApiResponse verify(LeaseInfo leaseInfo);
}
