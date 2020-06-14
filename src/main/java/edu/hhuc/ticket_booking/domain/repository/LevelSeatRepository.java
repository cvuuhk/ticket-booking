package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.LevelSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LevelSeatRepository extends JpaRepository<LevelSeat, Integer>, JpaSpecificationExecutor<LevelSeat> {

}