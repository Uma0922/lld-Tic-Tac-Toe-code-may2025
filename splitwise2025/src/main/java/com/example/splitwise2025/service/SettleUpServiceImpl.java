package com.example.splitwise2025.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.splitwise2025.exception.SettleUpException;
import com.example.splitwise2025.model.Expense;
import com.example.splitwise2025.model.ExpenseUser;
import com.example.splitwise2025.model.User;
import com.example.splitwise2025.repo.ExpenseUserRepository;
import com.example.splitwise2025.repo.UserRepository;
import com.example.splitwise2025.service.impl.ISettleUpService;
import com.example.splitwise2025.strategy.iSettleUpStrategy;

@Service
public class SettleUpServiceImpl implements ISettleUpService {

    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;
    private final iSettleUpStrategy settleUpStrategy;
    
    @Autowired
    public SettleUpServiceImpl(@Qualifier("minMaxHeapSettleUpStrategy") iSettleUpStrategy settleUpStrategy, 
    UserRepository userRepository,ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
    }


    @Override
    public List<Expense> settleUp(Long userId) throws SettleUpException {
        /**
         * S1. Fetch the user by userId. If doesn't exist throw exception.
         * S2. For the given user, get me all the List<ExpenseUsers> 
         * S3. Get the object by some factor. 
         * S4. Finally Return the List<Transactions>
         * 
         */

         Optional<User> existingUser =  userRepository.findById(userId);
         if(!existingUser.isPresent()){
            throw new SettleUpException("User does not exist.");
         }

        User user = existingUser.get();
        List<ExpenseUser> expensesForUser =  expenseUserRepository.findAllByUser(user);
        
        List<Expense> expenses = new ArrayList<>();
        for(ExpenseUser eu : expensesForUser){
            expenses.add(eu.getExpense());
        }
        
    
        List<Expense> transactionsList =   settleUpStrategy.calculateTransactions(expenses);
        return transactionsList;
    }
    




}
