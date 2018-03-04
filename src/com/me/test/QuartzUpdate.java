package com.me.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.service.ImproveInformtionService;

@Service
public class QuartzUpdate {

	@Autowired
	private ImproveInformtionService improveInformtionService;

	public void update() {
		improveInformtionService.updateExpressOrder();

	}

	public void updateToken() {

	}

}
