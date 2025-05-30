package com.scaler.bms2025.bmsmay2025.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.bms2025.bmsmay2025.model.Show;

@Repository
public interface ShowRepo extends JpaRepository<Show,Long> {
    
}
