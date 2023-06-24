package com.project1.eshop.service.Impl;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;
import com.project1.eshop.exception.TransactionNotAllowedException;
import com.project1.eshop.repository.TransactionRepository;
import com.project1.eshop.service.CartItemService;
import com.project1.eshop.service.TransactionProductService;
import com.project1.eshop.service.TransactionService;
import com.project1.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService, CartItemService cartItemService, TransactionProductService transactionProductService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
    }


    @Override
    public TransactionDetailsData createTransaction(FirebaseUserData firebaseUserData){
        UserEntity userEntity =userService.getEntityByFirebaseUserData(firebaseUserData);

        List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemByUid(userEntity.getUid());
        if(cartItemEntityList.isEmpty()){
            throw new TransactionNotAllowedException("No item in cart");
        }
        TransactionEntity transaction = new TransactionEntity(userEntity);
        transactionRepository.save(transaction);

        for (CartItemEntity cartItemEntity: cartItemEntityList){
            TransactionProductEntity transactionProductEntity =transactionProductService.createTransactionProduct(cartItemEntity,transaction);
            transaction.getTransactionProductList().add(transactionProductEntity);
            transaction.setTotal(transaction.getTotal().add(transactionProductEntity.getSubtotal()));
        }
        transactionRepository.save(transaction);

        return new TransactionDetailsData(transaction);
    }
}
