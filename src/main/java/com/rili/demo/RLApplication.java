package com.rili.demo;

import com.rili.demo.dao.CustomerDao;
import com.rili.demo.entity.CustomerEntity;
import com.rili.demo.entity.SearchParam;
import com.rili.demo.entity.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@EnableAutoConfiguration
@SpringBootApplication
public class RLApplication {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/search/{searchValue}/{pageIndex}/{pageSize}")
    @ResponseBody
    List<SearchResult> home(@PathVariable String searchValue,
                              @PathVariable Integer pageIndex,
                              @PathVariable Integer pageSize) {
        SearchParam param = new SearchParam();
        param.setSearchValue(searchValue);
        param.setPageIndex(pageIndex);
        param.setPageSize(pageSize);
        List<SearchResult> resultList = customerDao.find(param);
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RLApplication.class, args);
    }


}
