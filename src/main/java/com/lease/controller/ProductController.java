package com.lease.controller;

import com.lease.api.ApiResponse;
import com.lease.domain.ProductInfo;
import com.lease.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-01
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/buyList")
    public ApiResponse buyList(@RequestBody ProductInfo productInfo) {
        return ApiResponse.getInstance(productService.selectList(productInfo));
    }

    @RequestMapping("/save")
    public ApiResponse save(@RequestBody ProductInfo productInfo) {
        return productService.save(productInfo);
    }

    @RequestMapping("/del")
    public ApiResponse del(Integer id) {
        return productService.del(id);
    }

    @RequestMapping("/getByid")
    public ApiResponse get(Integer id) {
        ProductInfo productInfo = productService.getByid(id);
        return ApiResponse.getInstance(productInfo);
    }
}
