package com.example.splitwise2025.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splitwise2025.dto.SettleUpRepsonseDTO;
import com.example.splitwise2025.service.impl.ISettleUpService;

@RestController
public class SettleUpController {

    private final ISettleUpService iSettleUpService;

    
    @Autowired
    public SettleUpController(ISettleUpService iSettleUpService) {
        this.iSettleUpService = iSettleUpService;
    }


    @PostMapping("/settleup/{userId}")
    public SettleUpRepsonseDTO settleUpForUser(@PathVariable("userId") Long userId){
        List<String> transactionsToExecute =  iSettleUpService.settleUp(userId);

        return null;
    }
}
