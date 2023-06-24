package com.project1.eshop.repository;

import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity> findByTidAndUser(Integer tid, UserEntity userEntity);
}
