package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 场次——票档表，用来确定票档
 */
@Entity
@Table(name = "session_level")
@Data
public class SessionLevel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;
    
    @Column(name = "product_session_id", nullable = false)
    private Integer productSessionId;
    
    /**
     * 票档名称
     */
    @Column(name = "nameZh", nullable = false)
    private String nameZh;
    
    /**
     * 票档价格
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    @OneToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "level_seat",
    //        joinColumns = {@JoinColumn(name = "id")},
    //        inverseJoinColumns = {@JoinColumn(name = "session_level_id")}
    //)
    @JoinColumn(name = "session_level_id")
    private List<LevelSeat> levelSeatList;
}