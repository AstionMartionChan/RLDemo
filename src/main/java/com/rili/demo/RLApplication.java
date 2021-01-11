package com.rili.demo;

import com.rili.demo.dao.CustomerDao;
import com.rili.demo.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    @RequestMapping("/")
    @ResponseBody
    List<CustomerEntity> home() {
        return customerDao.find();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RLApplication.class, args);
    }


}
