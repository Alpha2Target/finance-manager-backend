package com.financemanager.financemanager.service;

import com.financemanager.financemanager.dto.response.CategoryExpenseResponse;
import com.financemanager.financemanager.dto.response.MonthlySummaryResponse;
import com.financemanager.financemanager.dto.response.ReportResponse;
import com.financemanager.financemanager.entity.Transaction;
import com.financemanager.financemanager.enums.TransactionType;
import com.financemanager.financemanager.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransactionRepository transactionRepository;

    // OVERALL REPORT
    public ReportResponse getOverallReport() {

        List<Transaction> transactions =
                transactionRepository.findAll();

        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expense = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {

            if (transaction.getType() ==
                    TransactionType.INCOME) {

                income = income.add(
                        transaction.getAmount()
                );

            } else {

                expense = expense.add(
                        transaction.getAmount()
                );
            }
        }

        return new ReportResponse(
                income,
                expense,
                income.subtract(expense)
        );
    }

    // MONTHLY SUMMARY
    public MonthlySummaryResponse getMonthlySummary(
            int month,
            int year
    ) {

        LocalDate startDate =
                LocalDate.of(year, month, 1);

        LocalDate endDate =
                startDate.withDayOfMonth(
                        startDate.lengthOfMonth()
                );

        List<Transaction> transactions =
                transactionRepository
                        .findByTransactionDateBetween(
                                startDate,
                                endDate
                        );

        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expense = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {

            if (transaction.getType() ==
                    TransactionType.INCOME) {

                income = income.add(
                        transaction.getAmount()
                );

            } else {

                expense = expense.add(
                        transaction.getAmount()
                );
            }
        }

        return new MonthlySummaryResponse(
                month,
                year,
                income,
                expense,
                income.subtract(expense)
        );
    }

    // CATEGORY EXPENSE REPORT
    public List<CategoryExpenseResponse>
    getCategoryExpenseReport() {

        List<Transaction> transactions =
                transactionRepository.findAll();

        Map<String, BigDecimal> expenseMap =
                new HashMap<>();

        for (Transaction transaction : transactions) {

            if (transaction.getType() ==
                    TransactionType.EXPENSE) {

                String categoryName =
                        transaction.getCategory().getName();

                expenseMap.put(
                        categoryName,

                        expenseMap.getOrDefault(
                                categoryName,
                                BigDecimal.ZERO
                        ).add(transaction.getAmount())
                );
            }
        }

        return expenseMap.entrySet()
                .stream()
                .map(entry ->
                        new CategoryExpenseResponse(
                                entry.getKey(),
                                entry.getValue()
                        )
                )
                .toList();
    }
}