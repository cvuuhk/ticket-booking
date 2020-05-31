package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.Account;
import edu.hhuc.ticket_booking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class AdminController{
    @Autowired
    AdminService adminService;
    
    @GetMapping(value = "/root")
    public String init(){ return "root"; }
    
    @PostMapping(value = "/root")
    public ResponseEntity<String> register(@RequestBody Account account){
        adminService.register(account);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    
    @GetMapping(value = "/admin")
    public String admin(){return "admin";}
}
