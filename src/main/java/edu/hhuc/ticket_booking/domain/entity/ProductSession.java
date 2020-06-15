package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 演出——场次表
 */
@Data
@Entity
@Table(name = "product_session")
public class ProductSession{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;
    
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    
    @Column(name = "city_id", nullable = false)
    private Integer cityId;
    
    @Column(name = "detail_position")
    private Integer detailPosition = null;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "session_level",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_session_id")}
    )
    private List<SessionLevel> sessionLevelList;
}