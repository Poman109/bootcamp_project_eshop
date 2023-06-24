package com.project1.eshop.data.cartItem.domainObject;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.product.domainObject.ProductDetailsData;
import com.project1.eshop.data.user.domainObject.UserDetailsData;

public class CartItemDetailsData {
    private Integer cid;
    private ProductDetailsData product;
    private UserDetailsData user;
    private Integer quantity;

    public CartItemDetailsData(CartItemEntity cartItemEntity){
        this.cid = cartItemEntity.getCid();
        this.product = new ProductDetailsData(cartItemEntity.getProduct());
        this.user = new UserDetailsData(cartItemEntity.getUser());
        this.quantity = cartItemEntity.getQuantity();

    }

    public UserDetailsData getUser() {
        return user;
    }

    public void setUser(UserDetailsData user) {
        this.user = user;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductDetailsData getProduct() {
        return product;
    }

    public void setProduct(ProductDetailsData product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
