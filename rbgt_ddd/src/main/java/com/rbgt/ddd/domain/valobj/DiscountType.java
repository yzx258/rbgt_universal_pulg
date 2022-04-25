package com.rbgt.ddd.domain.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lvweijie
 * @date 2021-08-13 14:37
 */
@Getter
@AllArgsConstructor
public enum DiscountType {

    NONE(0, ""), SUBTRACT(1, "优惠"), ADD(2, "加价"),;

    Integer code;
    String name;
}
