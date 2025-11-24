package com.zhong.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * sku信息
 * </p>
 *
 * @author ear
 * @since 2025-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sku_info")
@ApiModel(value="SkuInfo对象", description="sku信息")
public class SkuInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "平台属性分组id")
    @TableField("attr_group_id")
    private Long attrGroupId;

    @ApiModelProperty(value = "商品类型：0->普通商品 1->秒杀商品")
    @TableField("sku_type")
    private Boolean skuType;

    @ApiModelProperty(value = "sku名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "展示图片")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "限购个数/每天（0：不限购）")
    @TableField("per_limit")
    private Integer perLimit;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    @TableField("publish_status")
    private Boolean publishStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    @TableField("check_status")
    private Boolean checkStatus;

    @ApiModelProperty(value = "是否新人专享：0->否；1->是")
    @TableField("is_new_person")
    private Boolean isNewPerson;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "sku编码")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    @TableField("market_price")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "锁定库存")
    @TableField("lock_stock")
    private Integer lockStock;

    @ApiModelProperty(value = "预警库存")
    @TableField("low_stock")
    private Integer lowStock;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "仓库")
    @TableField("ware_id")
    private Long wareId;

    private Long version;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用）")
    @TableField("is_deleted")
    private Integer isDeleted;


}
