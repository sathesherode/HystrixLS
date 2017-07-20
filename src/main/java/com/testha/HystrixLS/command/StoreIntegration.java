package com.testha.HystrixLS.command;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "getFallbackStores",
    	    commandProperties = {
    	    	      @HystrixProperty(name="hystrix.command.default.circuitBreaker.requestVolumeThreshold", value="5"),
    	    	      @HystrixProperty(name="hystrix.command.default.execution.timeout.enabled", value="false")
    	    	    })
    public String getStores() {

    	RestTemplate template = new RestTemplate();
    	URI uri = URI.create("http://localhost:8089/myservice/gettempdata");
		return template.getForObject(uri, String.class);
    }

    public String getFallbackStores() {
        return "fallback message";
    }
}