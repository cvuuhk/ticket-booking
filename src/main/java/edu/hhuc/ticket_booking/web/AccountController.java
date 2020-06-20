package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.entity.AccountReal;
import edu.hhuc.ticket_booking.domain.entity.Ticket;
import edu.hhuc.ticket_booking.domain.repository.AccountRealRepository;
import edu.hhuc.ticket_booking.domain.repository.AccountRepository;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
import edu.hhuc.ticket_booking.domain.repository.TicketRepository;
import edu.hhuc.ticket_booking.service.AccountService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping(value = "/user")
@Log
public class AccountController{
    @Autowired
    AccountService        accountService;
    @Autowired
    AccountRepository     accountRepository;
    @Autowired
    AccountRealRepository accountRealRepository;
    @Autowired
    TicketRepository      ticketRepository;
    @Autowired
    ProductRepository     productRepository;
    
    @GetMapping(value = "")
    public String initUser(){ return "user"; }
    
    private Integer accountId = null;
    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity<Account> getUser(Principal principal){
        if(accountId != null){
            return new ResponseEntity<>(accountRepository.findAccountById(accountId), HttpStatus.OK);
        }
        Account account = accountRepository.findAccountByName(principal.getName());
        accountId = account.getId();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    
    @PutMapping(value = "")
    public void updateUser(@RequestBody Account account){
        Account old = accountRepository.findAccountById(account.getId());
        if(!old.getPassword().equals(account.getPassword())){
            account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        }
        accountRepository.save(account);
    }
    
    @PostMapping(value = "/unregister")
    public void unregister(@RequestBody Account account){
        account.setEnabled(false);
        accountRepository.save(account);
    }
    
    @GetMapping(value = "/accountReal/{accountId}")
    @ResponseBody
    public ResponseEntity<List<AccountReal>> initReal(@PathVariable Integer accountId){
        List<AccountReal> accountRealList = accountRealRepository.findRealsByAccountId(accountId);
        return new ResponseEntity<>(accountRealList, HttpStatus.OK);
    }
    
    @GetMapping(value = "/accountReal/buy")
    @ResponseBody
    public ResponseEntity<List<AccountReal>> initRealForBuy(Principal principal){
        if(accountId == null){
            accountId = accountRepository.findAccountByName(principal.getName()).getId();
        }
        List<AccountReal> accountRealList = accountRealRepository.findRealsByAccountId(accountId);
        return new ResponseEntity<>(accountRealList, HttpStatus.OK);
    }
    
    @PostMapping(value = "/accountReal")
    @ResponseBody
    public ResponseEntity<String> addReal(@RequestBody AccountReal accountReal){
        accountRealRepository.save(accountReal);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @PutMapping(value = "/accountReal")
    @ResponseBody
    public ResponseEntity<String> updateReal(@RequestBody AccountReal accountReal){
        accountRealRepository.save(accountReal);
        return new ResponseEntity<>("修改成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/accountReal/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteReal(@PathVariable Integer id){
        accountRealRepository.deleteById(id);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/buy/{productId}")
    public String initBuy(@PathVariable Integer productId){
        return "buy";
    }
    
    @PostMapping(value = "/buy")
    public ResponseEntity<String> buy(@RequestBody Ticket ticket){
        ticket.setAccountId(accountId);
        ticket.setCreateTime(LocalDateTime.now());
        ticketRepository.save(ticket);
        return new ResponseEntity<>("下单成功", HttpStatus.OK);
    }
    
    @PostMapping(value = "/ticket")
    @ResponseBody
    public ResponseEntity<List<Ticket>> getTicket(@RequestBody Integer accountId){
        List<Ticket> tickets = ticketRepository.findAllByAccountId(accountId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
