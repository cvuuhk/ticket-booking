package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{
    Product findProductById(Integer id);
    @Query("select a from Product a where a.down=false")
    List<Product> findAllAvaliableProduct();
}