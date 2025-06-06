package com.example.splitwise2025.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.splitwise2025.model.Expense;
import com.example.splitwise2025.model.Group;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    
}
