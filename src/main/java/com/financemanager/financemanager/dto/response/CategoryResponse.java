package com.financemanager.financemanager.dto.response;

import com.financemanager.financemanager.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse {

    private String name;

    private TransactionType type;

    private boolean isCustom;
}