package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.repository.AccountRepository;
import edu.hhuc.ticket_booking.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/root")
public class RootController{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RootService       service;
    
    @GetMapping(value = "")
    public String initRoot(){ return "root"; }
    
    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity<String> addAdmin(@RequestBody Account account){
        service.registerAdmin(account);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @PutMapping(value = "")
    @ResponseBody
    public ResponseEntity<String> updateAdmin(@RequestBody Account account){
        accountRepository.save(account);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "")
    @ResponseBody
    public ResponseEntity<String> deleteAdmin(@RequestBody Integer accountId){
        accountRepository.deleteById(accountId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/admins")
    public ResponseEntity<List<Account>> getAdmins(){
        return new ResponseEntity<>(service.getAdmins(), HttpStatus.OK);
    }
    
    @PutMapping(value = "/updateRootPassword")
    public ResponseEntity<String> updateRootPassword(@RequestBody String raw){
        return new ResponseEntity<>(service.updateRootPassword(raw), HttpStatus.OK);
    }
}
