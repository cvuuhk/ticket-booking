package edu.hhuc.ticket_booking.service;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.entity.AccountRole;
import edu.hhuc.ticket_booking.domain.repository.AccountRepository;
import edu.hhuc.ticket_booking.domain.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RootService{
    @Autowired
    AccountService        accountService;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    @Autowired
    AccountRepository     accountRepository;
    
    public String registerAdmin(Account account){
        accountService.register(account);
        AccountRole accountRole = accountRoleRepository.findAccountRoleByAccountId(account.getId());
        accountRole.setRoleId(2);
        accountRoleRepository.save(accountRole);
        
        return "success";
    }
    
    public List<Account> getAdmins(){
        return accountRepository.findAdmins();
    }
    
    public String updateRootPassword(String raw){
        String  password = new BCryptPasswordEncoder().encode(raw);
        Account root     = accountRepository.findAccountByName("root");
        root.setPassword(password);
        accountRepository.save(root);
        return "success";
    }
}
