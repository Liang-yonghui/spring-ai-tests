package com.rhine.domain.ai;

/**
 * 运算符
 *
 * @author chenzb
 * @date 2019/1/30
 */
public enum Operator {
    EQ,
    NOT_EQ,
    IN,
    NOT_IN,
    GT,
    GTE,
    LT,
    LTE,
    RANGE,
    CONTAIN_CS,
    CONTAIN_CI,
    LIKE,
    IS_NULL,
    IS_NOT_NULL,
    PATTERN_DATE,
    RECENT,
    RECENT_RANGE,
    BEFORE,
    TODAY
}
