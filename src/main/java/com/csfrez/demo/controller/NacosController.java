package com.csfrez.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class NacosController {

    @NacosValue(value = "${logging.level.root:INFO}", autoRefreshed = true)
    private String loglevel;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("loglevel")
    public String logLevel(){
        return this.loglevel;
    }

    @GetMapping("/count")
    public Integer count(){
        return jdbcTemplate.queryForObject("select count(1) from sys_log", Integer.class);
    }

}
