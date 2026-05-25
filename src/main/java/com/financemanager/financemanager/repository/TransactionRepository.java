package com.financemanager.financemanager.repository;

import com.financemanager.financemanager.entity.Transaction;
import com.financemanager.financemanager.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(
            TransactionType type
    );

    List<Transaction> findByTransactionDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<Transaction> findByCategoryName(
            String categoryName
    );
}