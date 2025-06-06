package com.example.splitwise2025.strategy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.splitwise2025.model.Expense;

@Service("bruteForceSettleUpStrategy")
public class BruteForceSettleUpStrategy implements iSettleUpStrategy {

    @Override
    public List<Expense> calculateTransactions(List<Expense> expensesForUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateTransactions'");
    }
    
}
