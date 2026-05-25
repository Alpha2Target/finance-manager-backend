package com.financemanager.financemanager.repository;

import com.financemanager.financemanager.entity.Category;
import com.financemanager.financemanager.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByDeletedFalse();

    boolean existsByNameAndUserId(
            String name,
            Long userId
    );

    List<Category> findByUserIdOrIsCustomFalse(
            Long userId
    );

    Category findByName(String name);
}