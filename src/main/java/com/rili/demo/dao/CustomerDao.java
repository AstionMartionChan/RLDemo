package com.rili.demo.dao;

import com.rili.demo.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerDao {

    List<CustomerEntity> find();

}
