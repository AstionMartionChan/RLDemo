package com.rili.demo;

import com.rili.demo.dao.CustomerDao;
import com.rili.demo.entity.CustomerEntity;
import com.rili.demo.entity.SearchParam;
import com.rili.demo.entity.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class RLApplication {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/search")
    @ResponseBody
    List<SearchResult> home(@RequestBody  SearchParam param) {
        if (param.getSearchValue().equals("")){
            return new ArrayList<>();
        } else {
            List<SearchResult> resultList = customerDao.find(param);
            return resultList;
        }
    }


    @RequestMapping("/get/{customerId}")
    @ResponseBody
    List<CustomerEntity> get(@PathVariable String customerId) {
        List<CustomerEntity> list = customerDao.getList(customerId);
        return list;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RLApplication.class, args);
    }


}
