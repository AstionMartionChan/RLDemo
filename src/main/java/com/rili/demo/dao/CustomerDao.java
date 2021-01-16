package com.rili.demo.dao;

import com.rili.demo.entity.CustomerEntity;
import com.rili.demo.entity.SearchParam;
import com.rili.demo.entity.SearchResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerDao {

    List<SearchResult> find(SearchParam param);

    List<CustomerEntity> getList(String customerId);

}
