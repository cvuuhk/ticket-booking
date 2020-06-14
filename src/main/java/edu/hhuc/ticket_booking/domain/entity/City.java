package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 城市设置
 */
@Table(name = "city")
@Entity
@Data
public class City {
    /**
     * 自增列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "CITY_ID", nullable = false)
    private Integer cityId;

    /**
     * 市代码
     */
    @Column(name = "CITY_CODE", nullable = false)
    private String cityCode;

    /**
     * 市名称
     */
    @Column(name = "CITY_NAME", nullable = false)
    private String cityName;

    /**
     * 简称
     */
    @Column(name = "SHORT_NAME", nullable = false)
    private String shortName;

    /**
     * 省代码
     */
    @Column(name = "PROVINCE_CODE")
    private String provinceCode = "NULL";

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer SORT = null;

    
}