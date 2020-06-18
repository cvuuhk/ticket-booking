package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City> {
    List<City> findAllByProvinceCode(String provinceCode);
}