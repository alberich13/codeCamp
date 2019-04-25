package com.indigo.twiiter.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import com.indigo.twiiter.TwiiterConfig;
import com.indigo.twiiter.service.TwiiterService;
import lombok.extern.slf4j.Slf4j;
import twitter4j.PagableResponseList;
import twitter4j.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ContextHierarchy({
@ContextConfiguration(classes = {TwiiterConfig.class})})
public class TwiiterTest {
	
	@Autowired
	private TwiiterService twiiterService;
	
	@Test
	public void sendSaveJmsList() throws InterruptedException {
		List<String> userFollowerList = twiiterService.printTest();
		userFollowerList.forEach(log::info);
	}
	
	@Test
	public void getingFollowersByUser() throws InterruptedException {
		List<String> userFollowerList = twiiterService.getUserFollowers("alemanuchau");
		userFollowerList.forEach(log::info);
	}
}
