package edu.hhuc.ticket_booking.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 省份设置
 */
@Entity
@Table(name = "province")
@Data
public class Province {
    /**
     * 自增列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVINCE_ID", insertable = false, nullable = false)
    private Integer provinceId;

    /**
     * 省份代码
     */
    @Column(name = "PROVINCE_CODE", nullable = false)
    private String provinceCode;

    /**
     * 省份名称
     */
    @Column(name = "PROVINCE_NAME", nullable = false)
    private String provinceName;

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