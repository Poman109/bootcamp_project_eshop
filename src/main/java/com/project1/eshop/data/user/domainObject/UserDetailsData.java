package com.project1.eshop.data.user.domainObject;

import com.project1.eshop.data.user.entity.UserEntity;
import jakarta.persistence.Column;

public class UserDetailsData {
    private Integer uid;
    private String firebaseUid;
    private String email;

    public UserDetailsData(UserEntity entity) {
        this.uid = entity.getUid();
        this.firebaseUid = entity.getFirebaseUid();
        this.email = getEmail();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
