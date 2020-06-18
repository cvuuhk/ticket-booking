package edu.hhuc.ticket_booking.web;
import edu.hhuc.ticket_booking.domain.entity.LevelSeat;
import edu.hhuc.ticket_booking.domain.entity.Product;
import edu.hhuc.ticket_booking.domain.entity.ProductSession;
import edu.hhuc.ticket_booking.domain.entity.SessionLevel;
import edu.hhuc.ticket_booking.domain.repository.LevelSeatRepository;
import edu.hhuc.ticket_booking.domain.repository.ProductRepository;
import edu.hhuc.ticket_booking.domain.repository.ProductSessionRepository;
import edu.hhuc.ticket_booking.domain.repository.SessionLevelRepository;
import edu.hhuc.ticket_booking.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/admin")
@Log
public class AdminController{
    @Autowired
    AdminService             adminService;
    @Autowired
    ProductRepository        productRepository;
    @Autowired
    ProductSessionRepository productSessionRepository;
    @Autowired
    SessionLevelRepository   sessionLevelRepository;
    @Autowired
    LevelSeatRepository      levelSeatRepository;
    
    @GetMapping(value = "")
    public String initAdmin(){return "admin";}
    
    @GetMapping(value = "/getAllProduct")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(value = "")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productRepository.save(product);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @PutMapping(value = "")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        productRepository.save(product);
        return new ResponseEntity<>("修改成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{productId}")
    @ResponseBody
    public ResponseEntity<String> downProduct(@PathVariable Integer productId){
        Product product = productRepository.findProductById(productId);
        product.setDown(true);
        productRepository.save(product);
        return new ResponseEntity<>("下架成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{productId}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
        productRepository.deleteById(productId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/session/{productId}")
    public String sessionInit(@PathVariable Integer productId){ return "session"; }
    
    @PostMapping(value = "/session/{productId}")
    @ResponseBody
    public ResponseEntity<List<ProductSession>> getSessions(@PathVariable Integer productId){
        List<ProductSession> productSessionList = productRepository.findProductById(productId).getProductSessionList();
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
    @ResponseBody
    public ResponseEntity<String> deleteSession(@PathVariable Integer sessionId){
        productSessionRepository.deleteById(sessionId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @GetMapping(value = "/level/{sessionId}")
    public String LevelInit(@PathVariable Integer sessionId){ return "level"; }
    
    @PostMapping(value = "/level/{sessionId}")
    @ResponseBody
    public ResponseEntity<List<SessionLevel>> getLevels(@PathVariable Integer sessionId){
        List<SessionLevel> sessionLevelList = productSessionRepository.findProductSessionById(sessionId).getSessionLevelList();
        return new ResponseEntity<>(sessionLevelList, HttpStatus.OK);
    }
    
    @PostMapping(value = "/level/update")
    @ResponseBody
    public ResponseEntity<String> updateLevel(@RequestBody SessionLevel level){
        sessionLevelRepository.save(level);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }
    
    @PostMapping(value = "/level/{sessionId}/add")
    @ResponseBody
    public ResponseEntity<String> addLevel(@PathVariable Integer sessionId, @RequestBody SessionLevel level){
        level.setProductSessionId(sessionId);
        sessionLevelRepository.save(level);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/level/{LevelId}")
    @ResponseBody
    public ResponseEntity<String> deleteLevel(@PathVariable Integer LevelId){
        sessionLevelRepository.deleteById(LevelId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/seat/{seatId}")
    @ResponseBody
    public ResponseEntity<String> deleteSeat(@PathVariable Integer seatId){
        levelSeatRepository.deleteById(seatId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
    
    @PostMapping(value = "/seat/{levelId}/{begin}/{end}")
    @ResponseBody
    public ResponseEntity<String> generateSeat(@PathVariable Integer levelId,
                                               @PathVariable Integer begin,
                                               @PathVariable Integer end){
        log.warning("levelId:"+levelId+
                "begin:"+begin+
                "end:"+end);
        LevelSeat    first = new LevelSeat();
        SessionLevel level = sessionLevelRepository.findSessionLevelById(levelId);
        ProductSession session = productSessionRepository
                .findProductSessionById(level.getProductSessionId());
        Product product = productRepository.findProductById(session.getProductId());
        String seatDetailPre = product.getTitle()+" "
                +session.getCity().getShortName()+"站 "
                +session.getStartTime()
                +level.getNameZh();
        
        first.setSessionLevelId(levelId);
        first.setSeatNumber(begin);
        String seatDetail = seatDetailPre+begin+"号座";
        first.setDetail(seatDetail);
        levelSeatRepository.saveAndFlush(first);
        int currentId = first.getId();
        currentId++;
        
        for(int i = begin+1; i <= end; i++){
            LevelSeat seat = new LevelSeat();
            seat.setId(currentId++);
            seat.setSessionLevelId(levelId);
            seat.setSeatNumber(i);
            seatDetail = seatDetailPre+i+"号座";
            seat.setDetail(seatDetail);
            levelSeatRepository.save(seat);
        }
        return new ResponseEntity<>("生成成功", HttpStatus.OK);
    }
}
