package com.financemanager.financemanager.controller;

import com.financemanager.financemanager.dto.request.CreateSavingsGoalRequest;
import com.financemanager.financemanager.dto.request.UpdateSavingsGoalRequest;
import com.financemanager.financemanager.dto.response.SavingsGoalResponse;
import com.financemanager.financemanager.service.SavingsGoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings-goals")
@RequiredArgsConstructor
public class SavingsGoalController {

    private final SavingsGoalService savingsGoalService;

    // CREATE GOAL
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsGoalResponse createGoal(

            @Valid
            @RequestBody
            CreateSavingsGoalRequest request
    ) {

        return savingsGoalService.createGoal(request);
    }

    // GET ALL GOALS
    @GetMapping
    public List<SavingsGoalResponse> getAllGoals() {

        return savingsGoalService.getAllGoals();
    }

    // UPDATE GOAL
    @PutMapping("/{id}")
    public SavingsGoalResponse updateGoal(

            @PathVariable Long id,

            @RequestBody
            UpdateSavingsGoalRequest request
    ) {

        return savingsGoalService.updateGoal(
                id,
                request
        );
    }

    // DELETE GOAL
    @DeleteMapping("/{id}")
    public String deleteGoal(
            @PathVariable Long id
    ) {

        return savingsGoalService.deleteGoal(id);
    }
}