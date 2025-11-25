package com.rhine.domain.ai;

/**
 * desc:分组字段的时间粒度
 * Created by zhaoxg at 2019-04-30 11:07
 */
public class GroupFieldOperator {
    private String field;
    /**周期类型
     *     YEAR
     *     MONTH
     *     WEEK
     *     DAY
     *     HOUR
     *     MINUTE
     *     SECOND
     *     MILLISECON
     */
    private String intervalType;
    private int interval;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
