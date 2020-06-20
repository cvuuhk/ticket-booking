package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.*;
import edu.hhuc.ticket_booking.domain.repository.*;
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
    AccountService           accountService;
    @Autowired
    ProvinceRepository       provinceRepository;
    @Autowired
    CityRepository           cityRepository;
    @Autowired
    AreaRepository           areaRepository;
    @Autowired
    StreetRepository         streetRepository;
    @Autowired
    ProductRepository        productRepository;
    @Autowired
    ProductSessionRepository productSessionRepository;
    
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
    @ResponseBody
    public ResponseEntity<List<Province>> getProvinces(){
        return new ResponseEntity<>(provinceRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/getCities/{provinceCode}")
    @ResponseBody
    public ResponseEntity<List<City>> getCityes(@PathVariable String provinceCode){
        return new ResponseEntity<>(cityRepository.findAllByProvinceCode(provinceCode), HttpStatus.OK);
    }
    
    @GetMapping(value = "/getAreas/{cityCode}")
    @ResponseBody
    public ResponseEntity<List<Area>> getAreas(@PathVariable String cityCode){
        return new ResponseEntity<>(areaRepository.findAllByCityCode(cityCode), HttpStatus.OK);
    }
    
    @GetMapping(value = "/getStreets/{areaCode}")
    @ResponseBody
    public ResponseEntity<List<Street>> getStreets(@PathVariable String areaCode){
        return new ResponseEntity<>(streetRepository.findAllByAreaCode(areaCode), HttpStatus.OK);
    }
    
    @PostMapping(value = "/session/{productId}")
    @ResponseBody
    public ResponseEntity<List<ProductSession>> getSessions(@PathVariable Integer productId){
        List<ProductSession> productSessionList = productRepository.findProductById(productId).getProductSessionList();
        return new ResponseEntity<>(productSessionList, HttpStatus.OK);
    }
    
    @PostMapping(value = "/level/{sessionId}")
    @ResponseBody
    public ResponseEntity<List<SessionLevel>> getLevels(@PathVariable Integer sessionId){
        List<SessionLevel> sessionLevelList = productSessionRepository.findProductSessionById(sessionId).getSessionLevelList();
        return new ResponseEntity<>(sessionLevelList, HttpStatus.OK);
    }
}
