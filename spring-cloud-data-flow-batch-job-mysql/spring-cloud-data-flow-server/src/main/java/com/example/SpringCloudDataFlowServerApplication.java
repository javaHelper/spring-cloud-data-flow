package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;
import org.springframework.cloud.deployer.spi.cloudfoundry.CloudFoundryDeployerAutoConfiguration;

@EnableDataFlowServer
@SpringBootApplication(exclude = { CloudFoundryDeployerAutoConfiguration.class})
public class SpringCloudDataFlowServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDataFlowServerApplication.class, args);
	}

}
