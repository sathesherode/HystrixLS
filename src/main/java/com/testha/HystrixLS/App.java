package com.testha.HystrixLS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testha.HystrixLS.command.MyHystrixCommand;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@RestController
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
   	public String showForm() {
       	/*StoreIntegration storeManager = new StoreIntegration();
       	String stores = storeManager.getStores();
   		return stores;*/
       	return new MyHystrixCommand().execute();
   	}
    
}
