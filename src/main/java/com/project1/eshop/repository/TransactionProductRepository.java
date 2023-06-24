package com.project1.eshop.repository;

import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity,Integer> {
}
