package com.example.splitwise2025.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.splitwise2025.service.impl.ISettleUpService;

@Service
public class SettleUpServiceImpl implements ISettleUpService {

    @Override
    public List<String> settleUp(Long userId) {
        /**
         * S1. Fetch the user by userId. If doesn't exist throw exception.
         * S2. For the given user, get me all the List<ExpenseUsers> 
         * S3. Get the object by some factor. 
         * S4. Finally Return the List<Transactions>
         * 
         */
    }
    




}
