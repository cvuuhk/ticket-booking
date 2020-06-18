package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 票档——座位表
 */
@Entity
@Data
@Table(name = "level_seat")
public class LevelSeat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;
    
    @Column(name = "session_level_id", nullable = false)
    private Integer sessionLevelId;
    
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;
    
    /**
     * 标题 + 城市名 + 场次开始时间 + 票档 + 价格 + 座位ID 如：202020 当我们谈论爱情 - 梁静茹世界巡回演唱会 上海站 2020-02-21 周五 19:30 五层看台 32号座，此字段在添加演唱会信息时自动生成
     */
    @Column(name = "detail", nullable = false)
    private String detail;
    
    @Column(name = "is_sold", nullable = false)
    boolean isSold = false;
}