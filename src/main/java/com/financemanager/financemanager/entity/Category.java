package com.financemanager.financemanager.entity;

import com.financemanager.financemanager.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    private boolean isCustom;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}