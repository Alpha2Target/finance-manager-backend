package com.financemanager.financemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategoryExpenseResponse {

    private String categoryName;

    private BigDecimal totalAmount;
}