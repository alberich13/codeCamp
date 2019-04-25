package com.indigo.twiiter.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Twitter;

public class TwiiterService {
	
	@Autowired
	private Twitter twitter;
	
	public List<String> getUserFollowers(String userName){
		return null;
	}
	
	public List<String> getCommonFriends(List<String> userNameList){
		return null;
	}
}
