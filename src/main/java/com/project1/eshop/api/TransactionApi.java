package com.project1.eshop.api;

import com.project1.eshop.config.EnvConfig;
import com.project1.eshop.data.SuccessResponseDto;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transaction.dto.TransactionResponseDto;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;
import com.project1.eshop.exception.TransactionNotAllowedException;
import com.project1.eshop.service.TransactionService;
import com.project1.eshop.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RestController
@RequestMapping("transaction")
public class TransactionApi {

    private final TransactionService transactionService;
    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createTransaction(JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        TransactionDetailsData createTransactionDetailsData = transactionService.createTransaction(firebaseUserData);
        TransactionResponseDto createTransactionResponseDto = new TransactionResponseDto(createTransactionDetailsData);
        return createTransactionResponseDto;
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionDetails(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(transactionService.getTransactionById(firebaseUserData,tid));
    }

    @PatchMapping("/{tid}/pay")
    public SuccessResponseDto updateTransactionStatus(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        if(transactionService.updateTransactionStatus(firebaseUserData,tid)){
            return new SuccessResponseDto();
        } else {
            throw new TransactionNotAllowedException("Cannot update transaction status");
        }
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishPayment(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(transactionService.finishPayment(firebaseUserData,tid));
    }



}
