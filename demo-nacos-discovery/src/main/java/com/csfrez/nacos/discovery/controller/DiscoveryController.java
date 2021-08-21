package com.csfrez.nacos.discovery.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @RequestMapping(value = "/set", method = GET)
    @ResponseBody
    public String set(@RequestParam String serviceName) {
        try {
            namingService.registerInstance(serviceName, "127.0.0.1", 8080); // 注册中心的地址
            //namingService.registerInstance(serviceName, "TEST_GROUP", "192.168.31.60", 8080); // 注册中心的地址
            //namingService.registerInstance();
            //namingService.deregisterInstance(serviceName, "127.0.0.1", 9090);
            return "OK";
        } catch (NacosException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @RequestMapping(value = "/del", method = GET)
    @ResponseBody
    public String del(@RequestParam String serviceName) {
        try {
            namingService.deregisterInstance(serviceName, "192.168.31.60", 8888);
            return "OK";
        } catch (NacosException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
