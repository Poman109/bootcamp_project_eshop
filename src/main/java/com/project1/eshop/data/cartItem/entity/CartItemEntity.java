package com.project1.eshop.data.cartItem.entity;

import com.project1.eshop.data.product.entity.ProductEntity;
import com.project1.eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @ManyToOne
    @JoinColumn(name="pid",nullable = false)
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name="uid",nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity() {
    }

    public CartItemEntity( ProductEntity product, UserEntity user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity pid) {
        this.product = pid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity uid) {
        this.user = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

