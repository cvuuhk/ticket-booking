package edu.hhuc.ticket_booking.web;

import edu.hhuc.ticket_booking.domain.Account;
import edu.hhuc.ticket_booking.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class MyController{
    @Autowired
    AccountRepository accountRepository;
    
    @GetMapping(value = "/register")
    public String initRegister(){ return "register"; }
    
    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<Object> register(@RequestBody Account account){
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.setRegisterTime(LocalDateTime.now());
        accountRepository.save(account);
        return new ResponseEntity<>("注册成功！", HttpStatus.OK);
    }
    
    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<Object> hello(Principal principal){
        String word = "Hello, "+principal.getName();
        return new ResponseEntity<>(word, HttpStatus.OK);
    }
}
