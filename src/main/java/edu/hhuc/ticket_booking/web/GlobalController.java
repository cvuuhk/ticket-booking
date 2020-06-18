package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Account;
import edu.hhuc.ticket_booking.domain.entity.City;
import edu.hhuc.ticket_booking.domain.entity.Province;
import edu.hhuc.ticket_booking.domain.repository.CityRepository;
import edu.hhuc.ticket_booking.domain.repository.ProvinceRepository;
import edu.hhuc.ticket_booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class GlobalController{
    @Autowired
    AccountService     accountService;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    CityRepository     cityRepository;
    
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
    
    @GetMapping(value = "/getProvinces")
    public ResponseEntity<List<Province>> getProvinces(){
        return new ResponseEntity<>(provinceRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/getCities/{provinceCode}")
    public ResponseEntity<List<City>> getCityes(@PathVariable String provinceCode){
        return new ResponseEntity<>(cityRepository.findAllByProvinceCode(provinceCode), HttpStatus.OK);
    }
}
