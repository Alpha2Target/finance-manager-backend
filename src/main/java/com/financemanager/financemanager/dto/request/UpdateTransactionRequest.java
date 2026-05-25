package com.financemanager.financemanager.dto.request;

import com.financemanager.financemanager.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateTransactionRequest {

    private BigDecimal amount;

    private String note;

    private TransactionType type;

    private String categoryName;
}