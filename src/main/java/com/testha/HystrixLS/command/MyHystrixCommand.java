package com.testha.HystrixLS.command;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class MyHystrixCommand extends HystrixCommand<String> {

	private RestTemplate template;

	public MyHystrixCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyHystrix"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						/*.withExecutionTimeoutInMilliseconds(50000)*/
						.withCircuitBreakerSleepWindowInMilliseconds(30000)
						.withCircuitBreakerRequestVolumeThreshold(3)));
		template = new RestTemplate();
	}

	@Override
	protected String run() throws Exception {
		URI uri = URI.create("http://localhost:8089/myservice/gettempdata");
		return template.getForObject(uri, String.class);
	}

	@Override
	protected String getFallback() {
		return new FallbackHystrixCommand().execute();
	}

}