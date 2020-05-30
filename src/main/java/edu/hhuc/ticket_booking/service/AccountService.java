package edu.hhuc.ticket_booking.service;
import edu.hhuc.ticket_booking.domain.Account;
import edu.hhuc.ticket_booking.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AccountService implements UserDetailsService{
    @Autowired
    AccountRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Account user = repository.findAccountByName(username);
        if(user == null){throw new UsernameNotFoundException("帐号不存在");}
        return user;
    }
}
