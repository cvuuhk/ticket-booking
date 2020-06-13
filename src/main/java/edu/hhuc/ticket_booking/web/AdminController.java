package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Product;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
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
    AdminService      adminService;
    @Autowired
    ProductRepository repository;
    
    @GetMapping(value = "")
    public String initAdmin(){return "admin";}
    
    @GetMapping(value = "/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(value = "")
    public void addProduct(@RequestBody Product product){
        repository.save(product);
    }
    
    @PutMapping(value = "")
    public void updateProduct(@RequestBody Product product){
        repository.save(product);
    }
    
    @DeleteMapping(value = "")
    public void deleteProduct(@RequestBody Integer productId){
        Product product = repository.findProductById(productId);
        product.setDown(true);
        repository.save(product);
    }
}
