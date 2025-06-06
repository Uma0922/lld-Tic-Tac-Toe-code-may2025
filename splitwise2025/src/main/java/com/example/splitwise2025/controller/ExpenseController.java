package com.example.splitwise2025.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splitwise2025.dto.CreateExpenseRequestDTO;
import com.example.splitwise2025.dto.ExpenseResponseDTO;
import com.example.splitwise2025.exception.ExpenseCreationException;
import com.example.splitwise2025.model.Expense;
import com.example.splitwise2025.service.impl.IExpenseService;

@RestController
public class ExpenseController {
    
    private final IExpenseService iExpenseService;

    @Autowired
    public ExpenseController(IExpenseService iExpenseService) {
        this.iExpenseService = iExpenseService;
    }


    @PostMapping("/expense")
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody CreateExpenseRequestDTO dto){
        try {
            Expense createdExpense =  iExpenseService.createExpense(dto.getDescription(), 
            dto.getTotalAmount(), dto.getUserIds(), dto.getSplitType());
            return ResponseEntity.ok(new ExpenseResponseDTO());
        } catch (ExpenseCreationException e) {
            System.out.println("Exception occurred... ");
            return (ResponseEntity<ExpenseResponseDTO>) ResponseEntity.internalServerError()
        }
        
    }

    
}
