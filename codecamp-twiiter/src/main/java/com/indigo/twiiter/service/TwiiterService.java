package com.indigo.twiiter.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;

@Component
public class TwiiterService {
	
	@Autowired
	private Twitter twitter;
	
	public List<String> getUserFollowers(String userName){
		List<String> userFollowers = Arrays.asList(new String[]{"UNO", "DOS"});;
		return userFollowers;
	}
	
	public List<String> getCommonFriends(List<String> userNameList){
		return null;
	}
}
