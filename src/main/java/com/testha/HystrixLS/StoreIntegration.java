package com.testha.HystrixLS;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores() {
    	RestTemplate template = new RestTemplate();
    	URI uri = URI.create("http://localhost:8089/myservice/gettempdata");
		return template.getForObject(uri, String.class);
    }

    public String defaultStores() {
        return "fallback message";
    }
}