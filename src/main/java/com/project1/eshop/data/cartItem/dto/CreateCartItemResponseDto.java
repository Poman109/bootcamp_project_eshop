package com.project1.eshop.data.cartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.eshop.data.cartItem.domainObject.CartItemDetailsData;

import java.math.BigDecimal;

public class CreateCartItemResponseDto {
    @JsonProperty("pid")
    private Integer pid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer quantity;
    @JsonProperty("stock")
    private Integer stock;

    public CreateCartItemResponseDto(CartItemDetailsData cartItemDetailsData){
        this.pid = cartItemDetailsData.getProduct().getPid();
        this.name = cartItemDetailsData.getProduct().getName();
        this.imageUrl = cartItemDetailsData.getProduct().getImageUrl();
        this.price = cartItemDetailsData.getProduct().getPrice();
        this.quantity = cartItemDetailsData.getQuantity();
        this.stock = cartItemDetailsData.getProduct().getStock();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

