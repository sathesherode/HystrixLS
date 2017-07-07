package com.testha.HystrixLS;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/create")
	public String showForm() {
		return new StoreIntegration().getStores();
	}
	
}