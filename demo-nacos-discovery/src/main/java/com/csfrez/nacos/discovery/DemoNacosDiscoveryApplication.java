package com.csfrez.nacos.discovery;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoNacosDiscoveryApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoNacosDiscoveryApplication.class, args);
	}

	@NacosInjected
	private NamingService namingService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		namingService.subscribe("demo-nacos-discovery", event -> {
			System.out.println(((NamingEvent)event).getServiceName());
			System.out.println(((NamingEvent)event).getInstances());
		});
	};

}
