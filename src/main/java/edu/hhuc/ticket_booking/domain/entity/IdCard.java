package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 证件表
 */
@Table(name = "ID_card")
@Entity
@Data
public class IdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type", insertable = false, nullable = false)
    private Integer type;

    /**
     * 证件类型名，如：身份证
     */
    @Column(name = "nameZh", nullable = false)
    private String nameZh;

    
}