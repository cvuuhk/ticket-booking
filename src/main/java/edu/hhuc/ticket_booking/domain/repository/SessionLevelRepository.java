package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.SessionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SessionLevelRepository extends JpaRepository<SessionLevel, Integer>, JpaSpecificationExecutor<SessionLevel> {

}