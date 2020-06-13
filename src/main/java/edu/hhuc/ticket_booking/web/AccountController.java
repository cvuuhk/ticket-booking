package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping(value = "/user")
public class AccountController{
    @Autowired
    AccountService accountService;
    
    @GetMapping(value = "")
    public String initUser(){return "user";}
    
    @PutMapping(value = "")
    public void updateUser(){ }
    
    @PostMapping(value = "/unregister")
    public void unregister(){}
    
    @GetMapping(value = "/real")
    public void initReal(){}
    
    @PostMapping(value = "/real")
    public void addReal(){}
    
    @PutMapping(value = "/real")
    public void updateReal(){}
    
    @DeleteMapping(value = "/real")
    public void deleteReal(){}
    
    @GetMapping(value = "/buy")
    public void initBuy(){}
    
    @PostMapping(value = "/buy")
    public void buy(){}
    
    @GetMapping(value = "/ticket")
    public void getTicket(){}
}
