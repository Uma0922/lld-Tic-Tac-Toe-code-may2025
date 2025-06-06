package com.example.splitwise2025.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.splitwise2025.exception.ExpenseCreationException;
import com.example.splitwise2025.model.Expense;

@Service
public interface IExpenseService {
    Expense createExpense(String description, double totalAmount,
     List<Long> userIds, String splitType) throws ExpenseCreationException;
}
