package com.project1.eshop.repository;

import com.project1.eshop.data.cartItem.entity.CartItemEntity;
import com.project1.eshop.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer> {

    @Query(value = "SELECT *FROM cart WHERE uid=?1 AND pid=?2",nativeQuery = true)
    Optional<CartItemEntity> findByUserUidAndProductPid(Integer uid, Integer pid);

    List<CartItemEntity> findAllByUser(UserEntity userEntity);

    Optional<List<CartItemEntity>> findByUserUid(Integer userId);
}