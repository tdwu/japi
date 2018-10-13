package com.pm.biz.login.model.base;


import com.pm.japi.annotations.ApiNote;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean<T> implements IPageBean<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    /**
     * 总数，当 total 为 null 或者大于 0 分页插件不在查询总数
     */
    @ApiNote("总页数")
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    @ApiNote("每页条数")
    private long size = 10;
    /**
     * 当前页
     */
    @ApiNote("当前页:从1开始")
    private long current = 1;

    /**
     * 查询数据列表
     */
    @ApiNote("当前页数据列表")
    private List<T> records = Collections.emptyList();
    /**
     * <p>
     * SQL 排序 ASC 数组
     * </p>
     */
    private String[] ascs;
    /**
     * <p>
     * SQL 排序 DESC 数组
     * </p>
     */
    private String[] descs;
    /**
     * <p>
     * 自动优化 COUNT SQL
     * </p>
     */
    private boolean optimizeCountSql = true;

    private Map<String,String> params=new HashMap<String,String>();

    public PageBean() {
        // to do nothing
    }

    /**
     * <p>
     * 分页构造函数
     * </p>
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public PageBean(long current, long size) {
        this(current, size, 0L);
    }

    public PageBean(long current, long size, Long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }

    /**
     * <p>
     * 是否存在上一页
     * </p>
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * <p>
     * 是否存在下一页
     * </p>
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public IPageBean<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public IPageBean<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public IPageBean<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public IPageBean<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String[] ascs() {
        return ascs;
    }

    public IPageBean<T> setAscs(List<String> ascs) {
        if (ascs!=null&&ascs.size()>0) {
            this.ascs = (String[]) ascs.toArray();
        }
        return this;
    }

    public IPageBean<T> setAscs(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    @Override
    public String[] descs() {
        return descs;
    }

    public IPageBean<T> setDescs(List<String> descs) {
        if (descs!=null&&descs.size()>0) {
            this.descs = (String[]) descs.toArray();
        }
        return this;
    }

    public IPageBean<T> setDescs(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    public IPageBean<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Map<String, String> condition() {
        return params;
    }
}
