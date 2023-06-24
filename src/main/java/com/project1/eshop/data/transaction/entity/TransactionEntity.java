package com.project1.eshop.data.transaction.entity;

import com.project1.eshop.data.transaction.TransactionStatus;
import com.project1.eshop.data.transactionProduct.entity.TransactionProductEntity;
import com.project1.eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private LocalDateTime datetime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProductList = new ArrayList<>();


    public TransactionEntity() {
    }
    public TransactionEntity(UserEntity user) {

        this.user = user;
        this.status = TransactionStatus.PREPARE;
        this.datetime = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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

    public List<TransactionProductEntity> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProductEntity> transactionProductList) {
        this.transactionProductList = transactionProductList;
    }
}


