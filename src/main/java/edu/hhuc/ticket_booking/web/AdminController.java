package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Product;
import edu.hhuc.ticket_booking.domain.entity.ProductSession;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
import edu.hhuc.ticket_booking.domain.repository.ProductSessionRepository;
import edu.hhuc.ticket_booking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/admin")
public class AdminController{
    @Autowired
    AdminService             adminService;
    @Autowired
    ProductRepository        repository;
    @Autowired
    ProductSessionRepository productSessionRepository;
    
    @GetMapping(value = "")
    public String initAdmin(){return "admin";}
    
    @GetMapping(value = "/getAllProduct")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(value = "")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        repository.save(product);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @PutMapping(value = "")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        repository.save(product);
        return new ResponseEntity<>("修改成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{productId}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
        Product product = repository.findProductById(productId);
        product.setDown(true);
        repository.save(product);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/session/{productId}")
    public String init(@PathVariable Integer productId){
        return "session";
    }
    
    @PostMapping(value = "/session/{productId}")
    @ResponseBody
    public ResponseEntity<List<ProductSession>> getSessions(@PathVariable Integer productId){
        List<ProductSession> productSessionList = repository.findProductById(productId).getProductSessionList();
        return new ResponseEntity<>(productSessionList, HttpStatus.OK);
    }
    
    @PostMapping(value = "/session/update")
    @ResponseBody
    public ResponseEntity<String> updateSession(@RequestBody ProductSession session){
        productSessionRepository.save(session);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }
    
    @PostMapping(value = "/session/{productId}/add")
    @ResponseBody
    public ResponseEntity<String> addSession(@PathVariable Integer productId, @RequestBody ProductSession session){
        session.setProductId(productId);
        productSessionRepository.save(session);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/session/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable Integer sessionId){
        productSessionRepository.deleteById(sessionId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
}
