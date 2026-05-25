package com.financemanager.financemanager.repository;

import com.financemanager.financemanager.entity.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsGoalRepository
        extends JpaRepository<SavingsGoal, Long> {
}