package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 街道设置
 */
@Table(name = "street")
@Entity
@Data
public class Street {
    /**
     * 自增列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "STREET_ID", nullable = false)
    private Integer streetId;

    /**
     * 街道代码
     */
    @Column(name = "STREET_CODE", nullable = false)
    private String streetCode;

    /**
     * 父级区代码
     */
    @Column(name = "AREA_CODE")
    private String areaCode = "NULL";

    /**
     * 街道名称
     */
    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    /**
     * 简称
     */
    @Column(name = "SHORT_NAME", nullable = false)
    private String shortName;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer SORT = null;

    
}