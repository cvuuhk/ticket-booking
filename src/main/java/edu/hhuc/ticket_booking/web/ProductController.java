package edu.hhuc.ticket_booking.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class ProductController{
    @GetMapping(value = "/product/{productId}")
    public void productDetails(@PathVariable String productId){}
}
