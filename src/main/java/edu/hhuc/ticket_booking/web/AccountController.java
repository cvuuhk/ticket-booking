package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.entity.Product;
import edu.hhuc.ticket_booking.domain.entity.Real;
import edu.hhuc.ticket_booking.domain.entity.Ticket;
import edu.hhuc.ticket_booking.domain.repository.AccountRepository;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
import edu.hhuc.ticket_booking.domain.repository.RealRepository;
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
import java.util.List;
@Controller
@RequestMapping(value = "/user")
@Log
public class AccountController{
    @Autowired
    AccountService    accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RealRepository    realRepository;
    @Autowired
    TicketRepository  ticketRepository;
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping(value = "")
    public String initUser(){ return "user"; }
    
    private Integer accountId = null;
    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity<Account> getUser(Principal principal){
        if(accountId != null){ return new ResponseEntity<>(accountRepository.findAccountById(accountId), HttpStatus.OK); }
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
    
    @GetMapping(value = "/real")
    public String initReal(){return "real";}
    
    @PostMapping(value = "/real")
    public void addReal(@RequestBody Real real){
        realRepository.save(real);
    }
    
    @PutMapping(value = "/real")
    public void updateReal(@RequestBody Real real){
        realRepository.save(real);
    }
    
    @DeleteMapping(value = "/real")
    public void deleteReal(@RequestBody Integer id){
        realRepository.deleteById(id);
    }
    
    @GetMapping(value = "/buy")
    @ResponseBody
    public ResponseEntity<Product> initBuy(@RequestBody Integer productId){
        return new ResponseEntity<>(productRepository.findProductById(productId), HttpStatus.OK);
    }
    
    @PostMapping(value = "/buy")
    public void buy(@RequestBody Ticket ticket){
        ticketRepository.save(ticket);
    }
    
    @PostMapping(value = "/ticket")
    @ResponseBody
    public ResponseEntity<List<Ticket>> getTicket(@RequestBody Integer accountId){
        List<Ticket> tickets = ticketRepository.findAllByAccountId(accountId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
