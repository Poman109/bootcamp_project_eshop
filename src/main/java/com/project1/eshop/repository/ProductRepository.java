package com.project1.eshop.repository;

import com.project1.eshop.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
    Optional<ProductEntity> findByPid(Integer pid);
}
