package com.financemanager.financemanager.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateSavingsGoalRequest {

    private String goalName;

    private BigDecimal targetAmount;

    private BigDecimal currentAmount;

    private LocalDate targetDate;
}