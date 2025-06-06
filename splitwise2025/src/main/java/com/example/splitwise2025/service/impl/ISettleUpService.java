package com.example.splitwise2025.service.impl;

import java.util.List;

import com.example.splitwise2025.exception.SettleUpException;
import com.example.splitwise2025.model.Expense;

public interface ISettleUpService {

    List<Expense> settleUp(Long userId) throws SettleUpException;
    
}
