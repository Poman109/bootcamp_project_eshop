package com.project1.eshop.api;

import com.project1.eshop.config.EnvConfig;
import com.project1.eshop.data.SuccessResponseDto;
import com.project1.eshop.data.cartItem.domainObject.CartItemDetailsData;
import com.project1.eshop.data.cartItem.dto.CreateCartItemResponseDto;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;
import com.project1.eshop.exception.UpdateCartItemNotAllowedException;
import com.project1.eshop.service.CartItemService;
import com.project1.eshop.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;
    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public SuccessResponseDto putCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        if(cartItemService.putCartItem(firebaseUserData,pid,quantity)){
            return new SuccessResponseDto();
        }
        throw new UpdateCartItemNotAllowedException("Cannot create");
    }


    @GetMapping()
    public List<CreateCartItemResponseDto> getCartItem(JwtAuthenticationToken jwtToken){
        List<CreateCartItemResponseDto> cartItemDetailResponseArray = new ArrayList<>();
        for(CartItemDetailsData cartItemDetailsData: cartItemService.getUserCart(JwtUtil.getFirebaseUserData(jwtToken))){
            cartItemDetailResponseArray.add(new CreateCartItemResponseDto(cartItemDetailsData));
        }
        return cartItemDetailResponseArray;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CreateCartItemResponseDto updateCartItemQuantity(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);

        return new CreateCartItemResponseDto(cartItemService.updateCartItemQuantity(firebaseUserData, pid, quantity));

    }

    @DeleteMapping("/{pid}")
    public SuccessResponseDto deleteCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        if(cartItemService.deletedCartItem(firebaseUserData,pid)){
            return new SuccessResponseDto();
        }
        throw new UpdateCartItemNotAllowedException("Cannot delete cart item.");
    }



}
