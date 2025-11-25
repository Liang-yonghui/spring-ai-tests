package com.rhine.domain.ai;

public class AggFieldOperator {
    private String field;
    private AggOperator operator;

    public AggFieldOperator() {
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public AggOperator getOperator() {
        return this.operator;
    }

    public void setOperator(AggOperator operator) {
        this.operator = operator;
    }
}
