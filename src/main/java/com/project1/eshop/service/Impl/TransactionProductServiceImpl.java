package com.project1.eshop.service.Impl;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import com.project1.eshop.repository.TransactionProductRepository;
import com.project1.eshop.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
   private final TransactionProductRepository transactionProductRepository;
    @Autowired
    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }


   @Override
    public TransactionProductEntity createTransactionProduct(CartItemEntity cartItemEntity, TransactionEntity transactionEntity){
        TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity,cartItemEntity);
        return transactionProductRepository.save(transactionProductEntity);
    }


}
