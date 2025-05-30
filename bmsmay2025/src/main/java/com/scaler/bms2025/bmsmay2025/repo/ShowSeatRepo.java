package com.scaler.bms2025.bmsmay2025.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.bms2025.bmsmay2025.model.ShowSeat;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findAllByShowAndSeat(long showId, List<Long> seatIds);
    void update(ShowSeat showSeat);
}
