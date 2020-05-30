package edu.hhuc.ticket_booking.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色表，定义各种角色
 */
@Data
@Table(name = "role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    /**
     * 角色名，以ROLE_开头
     */
    @Column(name = "name", nullable = false)
    private String name;

    
}