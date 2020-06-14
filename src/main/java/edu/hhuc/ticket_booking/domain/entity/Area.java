package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 地区设置
 */
@Entity
@Data
@Table(name = "area")
public class Area {
    /**
     * 自增列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AREA_ID", insertable = false, nullable = false)
    private Integer areaId;

    /**
     * 区代码
     */
    @Column(name = "AREA_CODE", nullable = false)
    private String areaCode;

    /**
     * 父级市代码
     */
    @Column(name = "CITY_CODE")
    private String cityCode = "NULL";

    /**
     * 市名称
     */
    @Column(name = "AREA_NAME", nullable = false)
    private String areaName;

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