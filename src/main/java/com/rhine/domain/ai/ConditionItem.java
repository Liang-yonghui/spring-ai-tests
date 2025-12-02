package com.rhine.domain.ai;

/**
 * 查询条件项
 *
 * @author chenzb
 * @date 2019/1/30
 */
public class ConditionItem {

    /** 字段 */
    private String field;

    /** 运算符 */
    private Operator operator;

    /** 运算值 */
    private Object value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
