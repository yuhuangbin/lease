package com.lease.service.impl;

import com.lease.api.ApiResponse;
import com.lease.domain.LeaseInfo;
import com.lease.domain.ProductInfo;
import com.lease.mapper.ProductInfoMapper;
import com.lease.service.ILeaseInfoService;
import com.lease.service.IProductService;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private ILeaseInfoService leaseInfoService;

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

    @Override
    public ApiResponse verify(LeaseInfo leaseInfo) {
        if (StringUtils.isNotEmpty(leaseInfo.getBoxCode())
                && checkIsExist(leaseInfo.getBoxCode(),1)) {
            return new ApiResponse(-3,"["+leaseInfo.getBoxCode()+"] 该集装箱编号不存在或正在出租中，无法添加！");
        }
        if (StringUtils.isNotEmpty(leaseInfo.getBedCode())
                && checkIsExist(leaseInfo.getBedCode(),2)) {
            return new ApiResponse(-3,"["+leaseInfo.getBedCode()+"] 该活动床编号不存在或正在出租中，无法添加！");
        }
        if (StringUtils.isNotEmpty(leaseInfo.getAirCode())
                && checkIsExist(leaseInfo.getAirCode(),3)) {
            return new ApiResponse(-3,"["+leaseInfo.getAirCode()+"] 该空调编号不存在或正在出租中，无法添加！");
        }
        return ApiResponse.SUCCESS;
    }

    private boolean checkIsExist(String productCode, Integer productType){
        ProductInfo param = new ProductInfo();
        param.setProductCode(productCode);
        param.setProductType(productType);
        param.setStatus(0);
        List<ProductInfo> productInfos = selectList(param);
        return productInfos.isEmpty() ? true : false;
    }

}
