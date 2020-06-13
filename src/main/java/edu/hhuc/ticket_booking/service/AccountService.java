package edu.hhuc.ticket_booking.service;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.repository.AccountRepository;
import edu.hhuc.ticket_booking.domain.entity.AccountRole;
import edu.hhuc.ticket_booking.domain.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AccountService implements UserDetailsService{
    @Autowired
    AccountRepository     accountRepository;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Account user = accountRepository.findAccountByName(username);
        if(user == null){throw new UsernameNotFoundException("帐号不存在");}
        return user;
    }
    
    public String register(Account account){
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.setRegisterTime(LocalDateTime.now());
        accountRepository.saveAndFlush(account);
        
        AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(account.getId());
        accountRole.setTimestamp(account.getRegisterTime());
        accountRoleRepository.save(accountRole);
        
        return "success";
    }
}
