package com.financemanager.financemanager.service;

import com.financemanager.financemanager.dto.request.AddTransactionRequest;
import com.financemanager.financemanager.dto.request.UpdateTransactionRequest;
import com.financemanager.financemanager.dto.response.TransactionResponse;
import com.financemanager.financemanager.entity.Category;
import com.financemanager.financemanager.entity.Transaction;
import com.financemanager.financemanager.repository.CategoryRepository;
import com.financemanager.financemanager.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    // ADD TRANSACTION
    public TransactionResponse addTransaction(
            AddTransactionRequest request
    ) {

        Category category =
                categoryRepository.findByName(
                        request.getCategoryName()
                );

        if (category == null) {
            throw new RuntimeException(
                    "Category not found"
            );
        }

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .note(request.getNote())
                .transactionDate(request.getTransactionDate())
                .type(request.getType())
                .category(category)
                .build();

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getNote(),
                savedTransaction.getTransactionDate(),
                savedTransaction.getType(),
                savedTransaction.getCategory().getName()
        );
    }

    // GET ALL TRANSACTIONS
    public List<TransactionResponse> getAllTransactions() {

        List<Transaction> transactions =
                transactionRepository.findAll();

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getNote(),
                        transaction.getTransactionDate(),
                        transaction.getType(),
                        transaction.getCategory().getName()
                ))
                .toList();
    }

    // DELETE TRANSACTION
    public String deleteTransaction(Long id) {

        Transaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transaction not found"
                                ));

        transactionRepository.delete(transaction);

        return "Transaction deleted successfully";
    }

    // FILTER BY CATEGORY
    public List<TransactionResponse> getTransactionsByCategory(
            String categoryName
    ) {

        List<Transaction> transactions =
                transactionRepository.findByCategoryName(
                        categoryName
                );

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getNote(),
                        transaction.getTransactionDate(),
                        transaction.getType(),
                        transaction.getCategory().getName()
                ))
                .toList();
    }

    // FILTER BY DATE RANGE
    public List<TransactionResponse> getTransactionsByDateRange(
            LocalDate startDate,
            LocalDate endDate
    ) {

        List<Transaction> transactions =
                transactionRepository.findByTransactionDateBetween(
                        startDate,
                        endDate
                );

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getNote(),
                        transaction.getTransactionDate(),
                        transaction.getType(),
                        transaction.getCategory().getName()
                ))
                .toList();
    }

    // UPDATE TRANSACTION
    public TransactionResponse updateTransaction(
            Long id,
            UpdateTransactionRequest request
    ) {

        Transaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transaction not found"
                                ));

        if (request.getAmount() != null) {
            transaction.setAmount(request.getAmount());
        }

        if (request.getNote() != null) {
            transaction.setNote(request.getNote());
        }

        if (request.getType() != null) {
            transaction.setType(request.getType());
        }

        if (request.getCategoryName() != null) {

            Category category =
                    categoryRepository.findByName(
                            request.getCategoryName()
                    );

            if (category == null) {
                throw new RuntimeException(
                        "Category not found"
                );
            }

            transaction.setCategory(category);
        }

        Transaction updatedTransaction =
                transactionRepository.save(transaction);

        return new TransactionResponse(
                updatedTransaction.getId(),
                updatedTransaction.getAmount(),
                updatedTransaction.getNote(),
                updatedTransaction.getTransactionDate(),
                updatedTransaction.getType(),
                updatedTransaction.getCategory().getName()
        );
    }
}