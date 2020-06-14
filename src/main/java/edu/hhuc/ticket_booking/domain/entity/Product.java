package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 演出表，描述演出信息
 */
@Table(name = "product")
@Entity
@Data
public class Product{
    /**
     * 定位一场演出
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;
    
    /**
     * 演出标题
     */
    @Column(name = "title", nullable = false)
    private String title;
    
    /**
     * 演出介绍
     */
    @Column(name = "details", nullable = false)
    private String details;
    
    /**
     * 是否下架
     */
    @Column(name = "is_down", nullable = false)
    private Boolean down = Boolean.FALSE;
    
    /**
     * 演出时长
     */
    @Column(name = "duration", nullable = false)
    private String duration = "'以现场为准'";
    
    /**
     * 入场时间
     */
    @Column(name = "start_time", nullable = false)
    private String startTime = "'请于开始前约 90 分钟入场'";
    
    /**
     * 最低演出曲目
     */
    @Column(name = "track_number", nullable = false)
    private Integer trackNumber;
    
    /**
     * 主要歌手
     */
    @Column(name = "singer", nullable = false)
    private String singer;
    
    /**
     * 最低演出时长
     */
    @Column(name = "minimum_duration", nullable = false)
    private String minimumDuration = "'以现场为准'";
    
    /**
     * 禁止携带物品
     */
    @Column(name = "prohibited_item", nullable = false)
    private String prohibitedItem = "'由于安保和版权的原因，大多数演出、展览及比赛场所禁止携带食品、饮料、专业摄录设备、打火机等物品，请您注意现场工作人员和广播的提示，予以配合。'";
    
    /**
     * 寄存说明
     */
    @Column(name = "deposit_Instructions", nullable = false)
    private String depositInstructions = "'无寄存处，请自行保管携带物品，谨防贵重物品丢失。'";
    
    /**
     * 初始开售时全场可售卖门票总张数
     */
    @Column(name = "initial_ticket_number", nullable = false)
    private String initialTicketNumber;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_session",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<ProductSession> productSessionList;
}