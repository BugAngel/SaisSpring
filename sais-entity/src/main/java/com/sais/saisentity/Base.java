package com.sais.saisentity;

import lombok.Setter;

public abstract class Base {
    //分页大小
    @Setter
    private Integer pageSize;
    //分页开始
    @Setter
    private Integer pageNum;

    @Setter
    private Integer total;

    //排序类型DESC  or  AES
    @Setter
    private String sort;

    @Setter
    private String orderBy;
}
