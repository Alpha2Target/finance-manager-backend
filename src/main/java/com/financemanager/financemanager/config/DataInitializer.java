package com.financemanager.financemanager.config;

import com.financemanager.financemanager.entity.Category;
import com.financemanager.financemanager.enums.TransactionType;
import com.financemanager.financemanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {

        createCategory("Salary", TransactionType.INCOME);

        createCategory("Food", TransactionType.EXPENSE);
        createCategory("Rent", TransactionType.EXPENSE);
        createCategory("Transportation", TransactionType.EXPENSE);
        createCategory("Entertainment", TransactionType.EXPENSE);
        createCategory("Healthcare", TransactionType.EXPENSE);
        createCategory("Utilities", TransactionType.EXPENSE);
    }

    private void createCategory(
            String name,
            TransactionType type
    ) {

        if (categoryRepository.findByName(name) == null) {

            Category category = Category.builder()
                    .name(name)
                    .type(type)
                    .isCustom(false)
                    .deleted(false)
                    .build();

            categoryRepository.save(category);
        }
    }
}