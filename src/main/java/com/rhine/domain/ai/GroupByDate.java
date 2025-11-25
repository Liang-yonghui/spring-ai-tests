package com.rhine.domain.ai;

import lombok.Data;

@Data
public class GroupByDate {
    private String field;
    private String granularity;
    private long interval;
}
