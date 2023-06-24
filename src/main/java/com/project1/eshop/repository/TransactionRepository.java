package com.project1.eshop.repository;

import com.project1.eshop.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
}
