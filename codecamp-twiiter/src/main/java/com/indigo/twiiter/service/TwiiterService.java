package com.indigo.twiiter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.PagableResponseList;
import twitter4j.User;

@Slf4j
@Component
public class TwiiterService {
	
	@Autowired
	private Twitter twitter;
	
	public List<String> printTest(){
		try {
			log.info("TW------------------------------------------------------");
			ResponseList<Status> responseList = twitter.getFavorites();
			responseList.stream().map(Status::getText).forEach(log::info);
			log.info("TW------------------------------------------------------");

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		List<String> userFollowers = Arrays.asList(new String[]{"UNO", "DOS"});;
		return userFollowers;
	}
	
	public List<String> getUserFollowers(String userName){
		PagableResponseList<User> users = null;
		List<String> followers = new ArrayList<String>();
		long count = -1;
		try {
			do {
				users = twitter.getFollowersList(userName, count);
				for(User user :users) {
					followers.add(user.getScreenName());
				}
			} while((count = users.getNextCursor()) != 0);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followers;
	}
	
	public List<String> getCommonFriends(String userNameA, String userNameB) {
		return null;
	}
}
