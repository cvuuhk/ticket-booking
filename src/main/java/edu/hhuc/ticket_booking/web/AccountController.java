package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.Account;
import edu.hhuc.ticket_booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class AccountController{
    @Autowired
    AccountService accountService;
    
    @GetMapping(value = "/login")
    public String login(){ return "login"; }
    
    @GetMapping(value = "/register")
    public String initRegister(){ return "register"; }
    
    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody Account account){
        accountService.register(account);
        return new ResponseEntity<>("注册成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/user")
    public String getUser(){return "user";}
}
