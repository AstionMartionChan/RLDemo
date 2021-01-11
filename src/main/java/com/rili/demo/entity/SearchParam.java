package com.rili.demo.entity;

import lombok.Data;

@Data
public class SearchParam {

    private String searchValue;
    private Integer pageIndex;
    private Integer pageSize;
}
