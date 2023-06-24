package com.project1.eshop.service.Impl;

import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;
import com.project1.eshop.repository.UserRepository;
import com.project1.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
        Optional<UserEntity> optionalUserEntity = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());
        if(optionalUserEntity.isEmpty()){
            UserEntity newUserEntity = new UserEntity(firebaseUserData);
            newUserEntity = userRepository.save(newUserEntity);
            return newUserEntity;
        }
        return optionalUserEntity.get();
    }

}
