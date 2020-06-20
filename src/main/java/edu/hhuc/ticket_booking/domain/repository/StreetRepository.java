package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Integer>, JpaSpecificationExecutor<Street> {
List<Street> findAllByAreaCode(String areaCode);
}