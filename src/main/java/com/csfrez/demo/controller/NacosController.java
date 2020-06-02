package com.csfrez.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
@Slf4j
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
        Integer count = jdbcTemplate.queryForObject("select count(1) from sys_log", Integer.class);
        log.info("count={}", count);
        return count;
    }

}
