package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 账户_角色表
 */
@Table(name = "account_role")
@Entity
@Data
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId = 1;

    /**
     * 授权时间
     */
    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    
}