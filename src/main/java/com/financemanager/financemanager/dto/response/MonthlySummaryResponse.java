package com.financemanager.financemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class MonthlySummaryResponse {

    private int month;

    private int year;

    private BigDecimal income;

    private BigDecimal expense;

    private BigDecimal balance;
}