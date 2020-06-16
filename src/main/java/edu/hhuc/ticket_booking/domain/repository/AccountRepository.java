package edu.hhuc.ticket_booking.domain.repository;

import edu.hhuc.ticket_booking.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>{
    Account findAccountByName(String name);
    @Query("select a "+
            "from Account a,AccountRole ar,Role r "+
            "where a.id = ar.accountId "+
            "and r.id = ar.roleId "+
            "and r.name='ROLE_admin'"+
            "order by a.id")
    List<Account> findAdmins();
    void deleteById(Integer id);
    Account findAccountById(Integer id);
}