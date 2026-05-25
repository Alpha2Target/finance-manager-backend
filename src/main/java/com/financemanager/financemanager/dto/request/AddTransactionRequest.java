package com.financemanager.financemanager.dto.request;

import com.financemanager.financemanager.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AddTransactionRequest {

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    private String note;

    @NotNull(message = "Transaction date is required")
    private LocalDate transactionDate;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotBlank(message = "Category name is required")
    private String categoryName;
}