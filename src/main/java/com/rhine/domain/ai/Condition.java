package com.rhine.domain.ai;

import java.util.List;

/**
 * 查询条件
 *
 * @author chenzb
 * @date 2019/1/30
 */
public class Condition {

    /** 查询条件关系 */
    private ConditionType cjt = ConditionType.AND;

    /** 查询条件项 */
    private List<ConditionItem> items;

    /** 内置查询条件 */
    private List<Condition> nestedConditions;

    public ConditionType getCjt() {
        return cjt;
    }

    public void setCjt(ConditionType cjt) {
        this.cjt = cjt;
    }

    public List<ConditionItem> getItems() {
        return items;
    }

    public void setItems(List<ConditionItem> items) {
        this.items = items;
    }

    public List<Condition> getNestedConditions() {
        return nestedConditions;
    }

    public void setNestedConditions(List<Condition> nestedConditions) {
        this.nestedConditions = nestedConditions;
    }
}
