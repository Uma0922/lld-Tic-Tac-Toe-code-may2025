package com.example.splitwise2025.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.example.splitwise2025.model.Expense;
import com.example.splitwise2025.model.ExpenseUser;
import com.example.splitwise2025.model.ExpenseUserType;
import com.example.splitwise2025.model.User;
import com.example.splitwise2025.utility.Pair;

@Service("minMaxHeapSettleUpStrategy")
public class MinMaxHeapSettleUpStrategy implements iSettleUpStrategy {

    @Override
    public List<Expense> calculateTransactions(List<Expense> expensesForUser) {
         Map<User,Double> userHadToPay = new HashMap<>(); /// -Ve HashMap
         Map<User,Double> userPaid = new HashMap<>(); // +HashMap

        for(Expense expense : expensesForUser){
           List<ExpenseUser> expenseUsers =  expense.getExpenseUsers();
           for(ExpenseUser expenseUser : expenseUsers){
            if(expenseUser.getExpenseUserType().equals(ExpenseUserType.HAD_TO_PAY)){
                Double existingAmount = userHadToPay.getOrDefault(expenseUser.getUser(), 0.0);
                userHadToPay.put(expenseUser.getUser(), existingAmount+ expenseUser.getAmount());
            }else if(expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID)){
                Double existingAmount = userPaid.getOrDefault(expenseUser.getUser(), 0.0);
                userPaid.put(expenseUser.getUser(), existingAmount+ expenseUser.getAmount());
            }
           }
        }
        

        PriorityQueue<Pair<User,Double>> negativeBalanceQueue = new PriorityQueue<>((p1,p2) -> Double.compare(p1.getValue(),p2.getValue()));
        for(Map.Entry<User,Double> entry: userHadToPay.entrySet()){
            negativeBalanceQueue.add(new Pair(entry.getKey(),entry.getValue()));
        }
        
        PriorityQueue<Pair<User,Double>> positiveBalanceQueue = new PriorityQueue<>((p1,p2) -> Double.compare(p2.getValue(),p1.getValue()));
        for(Map.Entry<User,Double> entry: userPaid.entrySet()){
            positiveBalanceQueue.add(new Pair(entry.getKey(),entry.getValue()));
        }

        List<Expense> transactions = new ArrayList<>();
        while(!negativeBalanceQueue.isEmpty() || !positiveBalanceQueue.isEmpty()){
            Pair<User,Double> negativeMin = negativeBalanceQueue.poll();
            Pair<User,Double> positiveMax = positiveBalanceQueue.poll();

            if(negativeMin.getValue() < positiveMax.getValue()){
                transactions.add(new Expense()); // Fullfill this object later.
                // This is basically: PB -- PA (Amount X)
                positiveBalanceQueue.add(positiveMax);
            }else{
                transactions.add(new Expense()); // Fullfill this object later.
                // This is basically: PA -- PB (Amount X)
                negativeBalanceQueue.add(negativeMin);
            }
        }

        return transactions;

    }
    
}
