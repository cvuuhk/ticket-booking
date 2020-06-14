package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.ProductSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSessionRepository extends JpaRepository<ProductSession, Integer>, JpaSpecificationExecutor<ProductSession> {

}