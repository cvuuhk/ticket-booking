package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer>, JpaSpecificationExecutor<AccountRole>{
    AccountRole findAccountRoleByAccountId(Integer accountId);
}