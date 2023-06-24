package com.project1.eshop.data.transaction.domainObject;

import com.project1.eshop.data.transaction.TransactionStatus;
import com.project1.eshop.data.transaction.entity.TransactionEntity;
import com.project1.eshop.data.transactionProduct.domainObject.TransactionProductDetailsData;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import com.project1.eshop.data.user.domainObject.UserDetailsData;
import com.project1.eshop.data.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsData {
    private Integer tid;
    private UserDetailsData user;
    private LocalDateTime datetime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductDetailsData> productList = new ArrayList<>();

    public TransactionDetailsData(TransactionEntity transaction){
        this.tid = transaction.getTid();
        this.user = new UserDetailsData(transaction.getUser());
        this.datetime = transaction.getDatetime();
        this.status = transaction.getStatus();
        this.total = transaction.getTotal();
        setProductList(transaction);

    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserDetailsData getUser() {
        return user;
    }

    public void setUser(UserDetailsData user) {
        this.user = user;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductDetailsData> getProductList() {
        return productList;
    }

    public void setProductList(List<TransactionProductDetailsData> productList) {
        this.productList = productList;
    }

    public void setProductList(TransactionEntity transaction) {
        for(TransactionProductEntity transactionProduct:transaction.getTransactionProductList()){
            this.productList.add(new TransactionProductDetailsData(transactionProduct));
        }
    }
}
