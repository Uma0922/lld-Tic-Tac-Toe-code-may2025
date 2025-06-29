package com.example.splitwise2025.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_group")
@Getter
@Setter
public class Group extends BaseModel {
    private String name;

    @ManyToMany
    private List<User> members;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    @ManyToOne
    private User createdBy;
}
