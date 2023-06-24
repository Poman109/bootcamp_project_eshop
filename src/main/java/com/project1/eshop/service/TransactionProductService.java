package com.project1.eshop.service;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;

public interface TransactionProductService {

    TransactionProductEntity createTransactionProduct(CartItemEntity cartItemEntity, TransactionEntity transactionEntity);
}
