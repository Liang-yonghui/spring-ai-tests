package com.rhine.domain.ai;

import lombok.Data;

import java.util.List;

@Data
public class Query {

    /**
     * 高级sql查询语句
     */
    private String sql;

    /**
     * 是否需要统计满足条件记录总数
     */
    private boolean needCount = true;

    /**
     * 分页查询时，每页的记录数，默认50，当赋值-1时表示不分页
     */
    private int pageSize = -1;

    /**
     * 分页查询时，当前的页码，从1开始计算，默认为1
     */
    private int pageIndex = 1;

    /**
     * 指定字段，查询结果按顺序返回指定字段的数据, 默认为所有字段
     */
    private List<String> fields;

    /**
     * 用于分组的字段， 指定了分组字段后， fields 属性自动失效， 返回的字段包括分组的字段和聚合操作的字段
     */
    private List<String> groupByFields;

    /**
     * 含有时间粒度的分组,与groupByFields 联合使用
     */
    private GroupFieldOperator groupFieldOperator;

    private GroupByDate groupByDate;

    /**
     * 聚合操作，指定聚合操作后， fields 属性失效， 返回的字段包括分组的字段和聚合操作的字段
     */
    private List<AggFieldOperator> aggFieldOps;

    /**
     * 查询条件
     */
    private Condition condition;

    /**
     * dataBank排序
     *
     * @return
     */
    private Order order;

    /**
     * 排序
     *
     * @return
     */
    private List<Order> orders;

    /**
     * 二次查询查询
     */
    private Query subQuery;

    /**
     * 获取维度字典数据
     */
    private List<GroupByDict> groupByDicts;

    /**
     * 获取字段标签字典数据
     */
    private List<GroupByLabel> groupByLabels;

    private List<DataSetParam> parameters;
}
