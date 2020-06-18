package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 订单表
 */
@Table(name = "ticket")
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "receive_address", nullable = false)
    private Integer receiveAddress;

    @Column(name = "receiver_email", nullable = false)
    private String receiverEmail;

    @Column(name = "detail_address")
    private String detailAddress = "NULL";

    @Column(name = "level_seat_id", nullable = false)
    private Integer levelSeatId;

    
}