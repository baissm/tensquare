package com.baissy.tensquare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author chenlin
 * @create 2020/4/30/23:56
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
