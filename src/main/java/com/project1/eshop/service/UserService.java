package com.project1.eshop.service;

import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData);


}