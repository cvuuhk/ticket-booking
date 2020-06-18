package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.Product;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class ProductController{
    @Autowired
    ProductRepository repository;
    
    @GetMapping(value = "/product")
    public ResponseEntity<List<Product>> index(){
        return new ResponseEntity<>(repository.findAllAvaliableProduct(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/product/{productId}")
    @ResponseBody
    public ResponseEntity<Product> productDetails(@PathVariable Integer productId){
        return new ResponseEntity<>(repository.findProductById(productId), HttpStatus.OK);
    }
}
