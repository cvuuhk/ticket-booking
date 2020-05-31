package edu.hhuc.ticket_booking.service;
import edu.hhuc.ticket_booking.domain.Account;
import edu.hhuc.ticket_booking.domain.AccountRole;
import edu.hhuc.ticket_booking.domain.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService{
    @Autowired
    AccountService        accountService;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    
    public String register(Account account){
        accountService.register(account);
        AccountRole accountRole = accountRoleRepository.findAccountRoleByAccountId(account.getId());
        accountRole.setRoleId(2);
        accountRoleRepository.save(accountRole);
        
        return "success";
    }
}
