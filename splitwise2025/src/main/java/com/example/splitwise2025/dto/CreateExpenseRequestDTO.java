package com.example.splitwise2025.dto;

import java.util.List;

import lombok.Data;

@Data // @Getter @Setter @AllArgsConstructor
public class CreateExpenseRequestDTO {
    private String description;
    private List<Long> userIds;
    private String splitType;
    private double totalAmount;
}
