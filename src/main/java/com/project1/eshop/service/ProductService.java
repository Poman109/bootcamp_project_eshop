package com.project1.eshop.service;

import com.project1.eshop.data.product.domainObject.ProductDetailsData;
import com.project1.eshop.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductDetailsData> getAllProduct();

    ProductDetailsData getProductEntity(Integer pid);

    ProductEntity getProductEntityByPid(Integer pid);

    public Boolean checkProductEntityByPid(Integer pid);

    void deductProductStock(Integer pid, Integer quantity);
}
