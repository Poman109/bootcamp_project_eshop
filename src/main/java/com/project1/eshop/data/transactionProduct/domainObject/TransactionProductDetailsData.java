package com.project1.eshop.data.transactionProduct.domainObject;

import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductDetailsData {
    private Integer tpid;
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductDetailsData(TransactionProductEntity transactionProduct) {
        this.tpid = transactionProduct.getTpid();
        this.pid = transactionProduct.getPid();
        this.name = transactionProduct.getName();
        this.description = transactionProduct.getDescription();
        this.imageUrl = transactionProduct.getImageUrl();
        this.price = transactionProduct.getPrice();
        this.stock = transactionProduct.getStock();
        this.quantity = transactionProduct.getQuantity();
        this.subtotal = transactionProduct.getSubtotal();

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
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
