package com.lease.service.impl;

import com.lease.api.ApiResponse;
import com.lease.domain.ProductInfo;
import com.lease.mapper.ProductInfoMapper;
import com.lease.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-01
 */
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> selectAll() {
        return productInfoMapper.selectAll();
    }

    @Override
    public ApiResponse save(ProductInfo productInfo) {
        if (productInfo.getId() == null) {
            productInfo.setStatus(0);
            productInfo.setCreateDate(new Date());
            productInfoMapper.insertSelective(productInfo);
        } else {
            productInfoMapper.updateByPrimaryKeySelective(productInfo);
        }
        return ApiResponse.SUCCESS;
    }

    @Override
    public List<ProductInfo> selectList(ProductInfo productInfo) {

        return productInfoMapper.select(productInfo);
    }

    @Override
    public ApiResponse del(Integer id) {
        productInfoMapper.deleteByPrimaryKey(id);
        return ApiResponse.SUCCESS;
    }

    @Override
    public ProductInfo getByid(Integer id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }
}
