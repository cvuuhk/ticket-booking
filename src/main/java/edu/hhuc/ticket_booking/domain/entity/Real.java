package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 实名登记表
 */
@Data
@Entity
@Table(name = "real")
public class Real {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    /**
     * 对应账户ID
     */
    @Column(name = "account_id")
    private Integer accountId = null;

    /**
     * 真实姓名
     */
    @Column(name = "real_name", nullable = false)
    private String realName;

    /**
     * 证件类型
     */
    @Column(name = "ID_card_type", nullable = false)
    private Integer idCardType;

    /**
     * 证件号
     */
    @Column(name = "ID_card_number", nullable = false)
    private String idCardNumber;

    
}