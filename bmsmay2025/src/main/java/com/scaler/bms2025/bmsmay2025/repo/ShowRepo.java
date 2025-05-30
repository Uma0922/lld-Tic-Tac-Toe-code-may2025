package com.scaler.bms2025.bmsmay2025.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.bms2025.bmsmay2025.model.Show;
import com.scaler.bms2025.bmsmay2025.model.User;

@Repository
public interface ShowRepo extends JpaRepository<Show,Long> {
   
}
