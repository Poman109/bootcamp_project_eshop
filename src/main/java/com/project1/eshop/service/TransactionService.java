package com.project1.eshop.service;

import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.user.domainObject.FirebaseUserData;

public interface TransactionService {
    TransactionDetailsData createTransaction(FirebaseUserData firebaseUserData);

    TransactionDetailsData getTransactionById(FirebaseUserData firebaseUserData, Integer tid);

    Boolean updateTransactionStatus(FirebaseUserData firebaseUserData, Integer tid);

    TransactionDetailsData finishPayment(FirebaseUserData firebaseUserData, Integer tid);
}
