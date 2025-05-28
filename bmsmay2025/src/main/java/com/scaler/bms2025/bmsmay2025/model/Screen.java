package com.scaler.bms2025.bmsmay2025.model;

import java.util.List;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;
    @OneToMany
    private List<Seat> seats;
    
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // screen_features (screenId, ordervalue)
    private List<Feature> features;
}
