package com.example.splitwise2025.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.splitwise2025.exception.ExpenseCreationException;
import com.example.splitwise2025.model.Expense;
import com.example.splitwise2025.model.ExpenseType;
import com.example.splitwise2025.model.ExpenseUser;
import com.example.splitwise2025.model.User;
import com.example.splitwise2025.repo.ExpenseRepo;
import com.example.splitwise2025.repo.UserRepository;
import com.example.splitwise2025.service.impl.IExpenseService;

@Service
public class ExpenseServiceImpl implements IExpenseService {
    private final ExpenseRepo expenseRepo;
    private final UserRepository userRepository;

    
    @Autowired
    public ExpenseServiceImpl(ExpenseRepo expenseRepo, UserRepository userRepository) {
        this.expenseRepo = expenseRepo;
        this.userRepository = userRepository;
    }

    @Override
    public Expense createExpense(String description, double totalAmount, List<Long> userIds, String splitType) throws ExpenseCreationException {
        Expense newExpense = new Expense();
        
        List<ExpenseUser> expenseUsers = new ArrayList<>();
        for(Long userId: userIds){
            Optional<User> user=   userRepository.findById(userId);
            if(!user.isPresent()){
                throw new ExpenseCreationException("Error in creating expense!");
            }
            ExpenseUser expenseUser =  new ExpenseUser();
            expenseUser.setAmount(calculateAmountForUser(totalAmount, splitType, userIds.size())); 
            expenseUser.setExpenseUserType(null);
            expenseUser.setUser(user.get());
            
            // finall add the expenseUser
            expenseUsers.add(expenseUser);
        }

        newExpense.setExpenseUsers(expenseUsers);
        newExpense.setCreatedAt(new Date());
        newExpense.setExpenseType(ExpenseType.NORMAL);
        newExpense.setLastModifiedAt(new Date());
        newExpense.setDescription(description);
        newExpense.setTotalAmount(totalAmount);

        Expense expense =   expenseRepo.save(newExpense);
        return expense;
    }

    private double calculateAmountForUser(double totalAmount, String splitType, int totalUsersSize) {
        double amountForUser = 0;
        // 3 people, equally. (33 33 33)
        if(splitType.equals("EQUAL")){
            amountForUser = totalAmount/totalUsersSize;
        }
        return amountForUser;
    }
    
}
