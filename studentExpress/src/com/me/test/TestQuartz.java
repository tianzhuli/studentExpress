package com.me.test;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestQuartz {

	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	@Autowired
	private CronTriggerBean cronTrigger;

	Scheduler scheduler = schedulerFactory.getScheduler();

	@Test
	public void test() {
		String newExpression = "5/1 * * * * ?";
		try {
			cronTrigger.setCronExpression(newExpression);
			scheduler.scheduleJob(cronTrigger);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
