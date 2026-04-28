package com.asule.springbootreview.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author asule
 * @since 2025-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 酒店id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 酒店名称
     */
    @TableField("name")
    private String name;

    /**
     * 酒店地址
     */
    @TableField("address")
    private String address;

    /**
     * 酒店价格
     */
    @TableField("price")
    private Integer price;

    /**
     * 酒店评分
     */
    @TableField("score")
    private Integer score;

    /**
     * 酒店品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 所在城市
     */
    @TableField("city")
    private String city;

    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    @TableField("star_name")
    private String starName;

    /**
     * 商圈
     */
    @TableField("business")
    private String business;

    /**
     * 纬度
     */
    @TableField("latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField("longitude")
    private String longitude;

    /**
     * 酒店图片
     */
    @TableField("pic")
    private String pic;


}
