package com.zhong.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ear
 * @since 2025-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Faq对象", description="")
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Qdrant要求主键id一般是uuid，而不是数据库默认的那种自增主键id")
    private String id;

    @ApiModelProperty(value = "分类id：1-订单问题，2-支付问题，3-商品问题，4-账户问题，5-其他问题")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "问题内容")
    private String question;

    @ApiModelProperty(value = "答案内容")
    private String answer;

    @ApiModelProperty(value = "状态，0-金庸，1-启用")
    private Integer status;

    @ApiModelProperty(value = "使用次数")
    @TableField("use_count")
    private Long useCount;


}
