package com.jhjc.app.domain;

import java.util.List;

public class ReturnPage<T> {
    private List<T> datas;
    private long total;


    public ReturnPage(List<T> datas, long total) {
        this.datas = datas;
        this.total = total;
    }
    public ReturnPage() {
    }
    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
