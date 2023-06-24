package com.project1.eshop.utility;


import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData(JwtAuthenticationToken jwtToken){
        return new FirebaseUserData(jwtToken);

    }
}
