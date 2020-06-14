package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer>, JpaSpecificationExecutor<TicketStatus> {

}