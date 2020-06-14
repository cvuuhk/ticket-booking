package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 订单状态表
 */
@Data
@Table(name = "ticket_status")
@Entity
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    /**
     * 订单状态描述
     */
    @Column(name = "detail", nullable = false)
    private String detail;

    
}