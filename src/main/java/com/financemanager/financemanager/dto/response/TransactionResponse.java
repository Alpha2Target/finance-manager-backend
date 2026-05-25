package com.financemanager.financemanager.dto.response;

import com.financemanager.financemanager.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {

    private Long id;

    private BigDecimal amount;

    private String note;

    private LocalDate transactionDate;

    private TransactionType type;

    private String categoryName;
}