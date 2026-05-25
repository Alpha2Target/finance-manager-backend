package com.financemanager.financemanager.service;

import com.financemanager.financemanager.dto.request.CreateSavingsGoalRequest;
import com.financemanager.financemanager.dto.request.UpdateSavingsGoalRequest;
import com.financemanager.financemanager.dto.response.SavingsGoalResponse;
import com.financemanager.financemanager.entity.SavingsGoal;
import com.financemanager.financemanager.repository.SavingsGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsGoalService {

    private final SavingsGoalRepository savingsGoalRepository;

    // CREATE GOAL
    public SavingsGoalResponse createGoal(
            CreateSavingsGoalRequest request
    ) {

        SavingsGoal goal = SavingsGoal.builder()
                .goalName(request.getGoalName())
                .targetAmount(request.getTargetAmount())
                .currentAmount(
                        request.getCurrentAmount() != null
                                ? request.getCurrentAmount()
                                : BigDecimal.ZERO
                )
                .targetDate(request.getTargetDate())
                .completed(false)
                .build();

        SavingsGoal savedGoal =
                savingsGoalRepository.save(goal);

        return mapToResponse(savedGoal);
    }

    // GET ALL GOALS
    public List<SavingsGoalResponse> getAllGoals() {

        return savingsGoalRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE GOAL
    public SavingsGoalResponse updateGoal(
            Long id,
            UpdateSavingsGoalRequest request
    ) {

        SavingsGoal goal =
                savingsGoalRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Savings goal not found"
                                ));

        if (request.getGoalName() != null) {
            goal.setGoalName(request.getGoalName());
        }

        if (request.getTargetAmount() != null) {
            goal.setTargetAmount(request.getTargetAmount());
        }

        if (request.getCurrentAmount() != null) {

            goal.setCurrentAmount(
                    request.getCurrentAmount()
            );

            if (goal.getCurrentAmount()
                    .compareTo(goal.getTargetAmount()) >= 0) {

                goal.setCompleted(true);
            }
        }

        if (request.getTargetDate() != null) {
            goal.setTargetDate(request.getTargetDate());
        }

        SavingsGoal updatedGoal =
                savingsGoalRepository.save(goal);

        return mapToResponse(updatedGoal);
    }

    // DELETE GOAL
    public String deleteGoal(Long id) {

        SavingsGoal goal =
                savingsGoalRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Savings goal not found"
                                ));

        savingsGoalRepository.delete(goal);

        return "Savings goal deleted successfully";
    }

    // MAPPER
    private SavingsGoalResponse mapToResponse(
            SavingsGoal goal
    ) {

        return new SavingsGoalResponse(
                goal.getId(),
                goal.getGoalName(),
                goal.getTargetAmount(),
                goal.getCurrentAmount(),
                goal.getTargetDate(),
                goal.isCompleted()
        );
    }
}