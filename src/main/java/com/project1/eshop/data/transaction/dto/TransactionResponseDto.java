package com.project1.eshop.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.eshop.data.transaction.TransactionStatus;
import com.project1.eshop.data.transaction.domainObject.TransactionDetailsData;
import com.project1.eshop.data.transactionProduct.domainObject.TransactionProductDetailsData;
import com.project1.eshop.data.transactionProduct.dto.TransactionProductResponseDto;
import com.project1.eshop.data.user.domainObject.UserDetailsData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    @JsonProperty("tid")
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer uid;
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime datetime;
    @JsonProperty("status")
    private TransactionStatus status;
    @JsonProperty("total")
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransactionProductResponseDto> transactionProductList = new ArrayList<>();

    public TransactionResponseDto(TransactionDetailsData data){
        this.tid = data.getTid();
        this.uid = data.getUser().getUid();
        this.datetime = data.getDatetime();
        this.status = data.getStatus();
        this.total = data.getTotal();
        setTransactionProductList(data.getProductList());

    }



    public Integer getTransactionId() {
        return tid;
    }

    public void setTransactionId(Integer transactionId) {
        this.tid = transactionId;
    }

    public Integer getUserId() {
        return uid;
    }

    public void setUserId(Integer userId) {
        this.uid = userId;
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

    public List<TransactionProductResponseDto> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProductDetailsData> transactionProductList) {
        List<TransactionProductResponseDto> transactionProductionResponseDtoList = new ArrayList<>();
        for(TransactionProductDetailsData transactionProductDetailData: transactionProductList){
            transactionProductionResponseDtoList.add(new TransactionProductResponseDto(transactionProductDetailData));
        }
        this.transactionProductList = transactionProductionResponseDtoList;
    }
}
