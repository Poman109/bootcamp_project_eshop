package com.project1.eshop.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.eshop.data.product.domainObject.ProductDetailsData;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transactionProduct.domainObject.TransactionProductDetailsData;

import java.math.BigDecimal;

public class ProductDetailsResponseDto {
    private Integer pid;
    private String name;
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductDetailsResponseDto(ProductDetailsData productDetailsData){
        this.pid = productDetailsData.getPid();
        this.name = productDetailsData.getName();
        this.description = productDetailsData.getDescription();
        this.imageUrl = productDetailsData.getImageUrl();
        this.price = productDetailsData.getPrice();
        this.stock = productDetailsData.getStock();
    }

    public ProductDetailsResponseDto(TransactionProductDetailsData transactionProductDetailsData){
        this.pid = transactionProductDetailsData.getPid();
        this.name = transactionProductDetailsData.getName();
        this.description = transactionProductDetailsData.getDescription();
        this.imageUrl = transactionProductDetailsData.getImageUrl();
        this.price = transactionProductDetailsData.getPrice();
        this.stock = transactionProductDetailsData.getStock();
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
