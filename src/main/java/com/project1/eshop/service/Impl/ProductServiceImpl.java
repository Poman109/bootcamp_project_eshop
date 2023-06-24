package com.project1.eshop.service.Impl;

import com.project1.eshop.data.product.domainObject.ProductDetailsData;
import com.project1.eshop.data.product.entity.ProductEntity;
import com.project1.eshop.exception.ProductNotFoundException;
import com.project1.eshop.repository.ProductRepository;
import com.project1.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDetailsData> getAllProduct() {
        List<ProductDetailsData> productDetailsDataList = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            productDetailsDataList.add(new ProductDetailsData(productEntity));
        }
        return productDetailsDataList;
    }

    @Override
    public ProductDetailsData getProductEntity(Integer pid) {
     return new ProductDetailsData(getProductEntityByPid(pid));
    }





    @Override
    public ProductEntity getProductEntityByPid(Integer pid) {

        Optional<ProductEntity> productEntity = productRepository.findByPid(pid);
        if (productEntity.isPresent()) {
            return productEntity.get();
        }
        throw new ProductNotFoundException("Cannot found productId: " + pid);
    }

    @Override
    public Boolean checkProductEntityByPid(Integer pid) {

        Optional<ProductEntity> productEntity = productRepository.findByPid(pid);
        if (productEntity.isPresent()) {
            return true;
        }
        throw new ProductNotFoundException("Cannot found productId: " + pid);
    }








}
