package com.financemanager.financemanager.controller;

import com.financemanager.financemanager.dto.request.AddTransactionRequest;
import com.financemanager.financemanager.dto.request.UpdateTransactionRequest;
import com.financemanager.financemanager.dto.response.TransactionResponse;
import com.financemanager.financemanager.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // ADD TRANSACTION
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse addTransaction(
            @Valid @RequestBody AddTransactionRequest request
    ) {

        return transactionService.addTransaction(request);
    }

    // GET ALL TRANSACTIONS
    @GetMapping
    public List<TransactionResponse> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    // DELETE TRANSACTION
    @DeleteMapping("/{id}")
    public String deleteTransaction(
            @PathVariable Long id
    ) {

        return transactionService.deleteTransaction(id);
    }

    // FILTER BY CATEGORY
    @GetMapping("/category/{name}")
    public List<TransactionResponse> getTransactionsByCategory(
            @PathVariable String name
    ) {

        return transactionService
                .getTransactionsByCategory(name);
    }

    // FILTER BY DATE RANGE
    @GetMapping("/date-range")
    public List<TransactionResponse> getTransactionsByDateRange(

            @RequestParam LocalDate startDate,

            @RequestParam LocalDate endDate
    ) {

        return transactionService
                .getTransactionsByDateRange(
                        startDate,
                        endDate
                );
    }

    // UPDATE TRANSACTION
    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(

            @PathVariable Long id,

            @RequestBody UpdateTransactionRequest request
    ) {

        return transactionService.updateTransaction(
                id,
                request
        );
    }
}