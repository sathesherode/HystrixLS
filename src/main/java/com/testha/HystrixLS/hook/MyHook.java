package com.testha.HystrixLS.hook;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

public class MyHook extends HystrixCommandExecutionHook {
	
	private static boolean isCircuitAlreadyOpened = false;
	public <T> T endRunSuccess(HystrixCommand<T> commandInstance, T response) {
		System.out.println("success hook");
		isCircuitAlreadyOpened = false;
        return response;
    }
	
	public <T> void startFallback(HystrixCommand<T> commandInstance) {
		System.out.println("fallback hook" + commandInstance.isResponseShortCircuited());
		if(commandInstance.isResponseShortCircuited() && !isCircuitAlreadyOpened) {
			System.out.println("send mail");
			isCircuitAlreadyOpened = true;
		}
    }
}
