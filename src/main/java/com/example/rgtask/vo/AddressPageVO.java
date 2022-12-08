package com.example.rgtask.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddressPageVO extends PageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货地址id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否为默认地址1是0不是
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
