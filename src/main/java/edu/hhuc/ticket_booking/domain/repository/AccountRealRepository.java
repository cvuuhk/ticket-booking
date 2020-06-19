package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.AccountReal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AccountRealRepository extends JpaRepository<AccountReal, Integer>, JpaSpecificationExecutor<AccountReal>{
    List<AccountReal> findRealsByAccountId(Integer accountId);
}