package com.project1.eshop.service.Impl;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.transaction.TransactionStatus;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;
import com.project1.eshop.exception.TransactionNotAllowedException;
import com.project1.eshop.repository.TransactionRepository;
import com.project1.eshop.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final ProductService productService;
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    @Autowired
    public TransactionServiceImpl(ProductService productService, TransactionRepository transactionRepository, UserService userService,
                                  CartItemService cartItemService, TransactionProductService transactionProductService) {
        this.productService = productService;
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

    @Transactional
    @Override
    public TransactionDetailsData getTransactionById(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity userEntity =userService.getEntityByFirebaseUserData(firebaseUserData);

            return new TransactionDetailsData(getEntityByTidAndUser(tid,userEntity));

    }

    @Transactional
    @Override
    public Boolean updateTransactionStatus(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity userEntity =userService.getEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = getEntityByTidAndUser(tid,userEntity);

        if(transactionEntity.getStatus() !=TransactionStatus.PREPARE){
            throw new TransactionNotAllowedException("Transaction status not match");
        }
        for (TransactionProductEntity transactionProduct:transactionEntity.getTransactionProductList()){
                productService.deductProductStock(transactionProduct.getPid(),transactionProduct.getQuantity());
            }
        transactionEntity.setStatus(TransactionStatus.PROCESSING);
        transactionRepository.save(transactionEntity);

        return true;


    }


    @Override
    @Transactional
    public TransactionDetailsData finishPayment(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = getEntityByTidAndUser(tid,userEntity);

        if(transactionEntity.getStatus() !=TransactionStatus.PROCESSING){
            throw new TransactionNotAllowedException("Transaction status not match");
        }

        cartItemService.deletedUserCart(userEntity);
        transactionEntity.setStatus(TransactionStatus.SUCCESS);
        return new TransactionDetailsData(transactionEntity);

    }


    public TransactionEntity getEntityByTidAndUser(Integer tid,UserEntity userEntity){
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findByTidAndUser(tid,userEntity);
        if (optionalTransactionEntity.isEmpty()){
            throw new TransactionNotAllowedException("No this transaction in account.");
        } else{
            return optionalTransactionEntity.get();
        }
    }






}
