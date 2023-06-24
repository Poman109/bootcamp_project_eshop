package com.project1.eshop.service.Impl;

import com.project1.eshop.data.cartItem.domainObject.CartItemDetailsData;
import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.product.entity.ProductEntity;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;
import com.project1.eshop.exception.ProductNotFoundException;
import com.project1.eshop.exception.UpdateCartItemNotAllowedException;
import com.project1.eshop.repository.CartItemRepository;
import com.project1.eshop.service.CartItemService;
import com.project1.eshop.service.ProductService;
import com.project1.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final UserService userService;
    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, ProductService productService, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public Boolean putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        UserEntity userEntity =userService.getEntityByFirebaseUserData(firebaseUserData);



        ProductEntity productEntity = productService.getProductEntityByPid(pid);
        if(!checkHasStock(productEntity,quantity)){
            throw new UpdateCartItemNotAllowedException("Not enough stock!");
        }

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByUserUidAndProductPid(userEntity.getUid(),productEntity.getPid());
        if (optionalCartItemEntity.isEmpty()){
            CartItemEntity cartItemEntity = new CartItemEntity(productEntity,userEntity,quantity);
            cartItemRepository.save(cartItemEntity);
            return true;
        } else {
            if(!checkHasStock(productEntity,optionalCartItemEntity.get().getQuantity()+quantity)){
                throw new UpdateCartItemNotAllowedException("Not enough stock!");
            } else {
                optionalCartItemEntity.get().setQuantity(optionalCartItemEntity.get().getQuantity()+quantity);
                cartItemRepository.save(optionalCartItemEntity.get());
                return true;
            }
        }
    }


    @Override
    public List<CartItemDetailsData> getUserCart(FirebaseUserData firebaseUserData) {
        UserEntity userEntity = getUserEntity(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
        if(cartItemEntityList.isEmpty()){
            throw new ProductNotFoundException("No item in cart.");
        } else {
            List<CartItemDetailsData> cartItemDetailsDataList = new ArrayList<>();
            for (CartItemEntity cartItemEntity : cartItemRepository.findAllByUser(userEntity)) {
                cartItemDetailsDataList.add(new CartItemDetailsData(cartItemEntity));
            }
            return cartItemDetailsDataList;
        }
    }

    @Override
    public CartItemDetailsData updateCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        if (productService.getProductEntity(pid).getStock() < quantity) {
            throw new UpdateCartItemNotAllowedException("Not enough stock!");
        }

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByUserUidAndProductPid(userEntity.getUid(), pid);
        if (optionalCartItemEntity.isEmpty()) {
            throw new ProductNotFoundException("No this product in cart");
        } else {
            if (quantity == 0) {
                optionalCartItemEntity.get().setQuantity(quantity);
                cartItemRepository.delete(optionalCartItemEntity.get());
                return new CartItemDetailsData(optionalCartItemEntity.get());
            }
            optionalCartItemEntity.get().setQuantity(quantity);
            cartItemRepository.save(optionalCartItemEntity.get());
            return new CartItemDetailsData(optionalCartItemEntity.get());
        }
    }

    @Override
    public Boolean deletedCartItem(FirebaseUserData firebaseUserData, Integer pid){
        UserEntity userEntity =userService.getEntityByFirebaseUserData(firebaseUserData);

        if(productService.checkProductEntityByPid(pid)) {

            Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByUserUidAndProductPid(userEntity.getUid(), pid);
            if (optionalCartItemEntity.isEmpty()) {
                throw new UpdateCartItemNotAllowedException("No product id :" + pid + "in cart.");
            }
            cartItemRepository.delete(optionalCartItemEntity.get());
        }
        return true;
    }







    public Boolean checkHasStock(ProductEntity productEntity,Integer quantity){
        return productEntity.getStock()>= quantity;
    }

    public UserEntity getUserEntity(FirebaseUserData firebaseUserData){
        return userService.getEntityByFirebaseUserData(firebaseUserData);
    }


}
