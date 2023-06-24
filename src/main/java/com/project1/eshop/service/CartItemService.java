package com.project1.eshop.service;

import com.project1.eshop.data.cartItem.domainObject.CartItemDetailsData;
import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface CartItemService {
    Boolean putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemDetailsData> getUserCart(FirebaseUserData firebaseUserData);

    CartItemDetailsData updateCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    Boolean deletedCartItem(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getCartItemByUid(Integer userId);
}
