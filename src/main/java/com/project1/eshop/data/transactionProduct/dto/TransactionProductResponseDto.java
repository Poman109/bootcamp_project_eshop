package com.project1.eshop.data.transactionProduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.eshop.data.product.dto.ProductDetailsResponseDto;
import com.project1.eshop.data.transactionProduct.domainObject.TransactionProductDetailsData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    @JsonProperty("tpid")
    private Integer tpid;
    @JsonProperty("product")
    private ProductDetailsResponseDto item;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("subtotal")
    private BigDecimal subtotal;


    public TransactionProductResponseDto(TransactionProductDetailsData data){
        this.tpid = data.getTpid();
        this.item = new ProductDetailsResponseDto(data);
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductDetailsResponseDto getItem() {
        return item;
    }

    public void setItem(ProductDetailsResponseDto item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
