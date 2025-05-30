package com.scaler.bms2025.bmsmay2025.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
public class User extends BaseModel {
    private String userName;
    private String email;
    private String password;
    

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // 1 --- M
    // 1  -- 1
    @OneToMany
    private List<Booking> bookings;
}
