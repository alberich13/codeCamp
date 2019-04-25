package com.indigo.twiiter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

@Slf4j
@Component
public class TwiiterService {
	
	@Autowired
	private Twitter twitter;
	
	public static int FOLLOWERS = 1;
	public static int FRIENDS = 2;
	
	@Value("${app.twitter.api.unlimitedapicalls}")
	private boolean unlimited_api_calls;
	
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
	
	public List<String> getCommonFollowers(String userNameA, String userNameB) {
		List<User> listFollowersUserA = this.getReationsOfUser(userNameA, TwiiterService.FOLLOWERS);
		List<User> listFollowersUserB = this.getReationsOfUser(userNameB, TwiiterService.FOLLOWERS);
		List<User> duplicates = this.findDuplicates(listFollowersUserA, listFollowersUserB);
		return duplicates.stream().map(user-> user.getScreenName()).collect(Collectors.toList());
	}
	
	public List<String> getCommonFriends(String userNameA, String userNameB) {
		List<String> commonFollowers = this.getCommonFollowers(userNameA, userNameB);
		List<String> commonFriends = new ArrayList<>();
		for (String follower : commonFollowers) {
			if (isXFollowerOfY(userNameA, follower) && isXFollowerOfY(userNameB, follower))
				commonFriends.add(follower);
		}
		return commonFriends;
	}
	
	private boolean isXFollowerOfY(String userNameX, String userNameY) {
		List<User> followersOfY = this.getReationsOfUser(userNameY, TwiiterService.FOLLOWERS);
		List<String> followersOfXOnString = followersOfY.stream().map(follower -> follower.getScreenName()).collect(Collectors.toList());
		return followersOfXOnString.contains(userNameX);
	}
	
	private List<User> getReationsOfUser(String userName, int typeOfRelationship) {
		PagableResponseList<User> users = null;
		List<User> relationed = new ArrayList<>();
		long count = -1;
		try {
			do {
				if (typeOfRelationship == TwiiterService.FOLLOWERS)
					users = twitter.getFollowersList(userName, count);
				if (typeOfRelationship == TwiiterService.FRIENDS)
					users = twitter.getFriendsList(userName, count);
				for (User user : users) {
					relationed.add(user);
				}
				if (this.unlimited_api_calls == false)
					break;
			} while ((count = users.getNextCursor()) != 0);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relationed;
	}
	
	private List<User> findDuplicates(List<User> listOne, List<User> listTwo) {
		return listOne.stream().map(userOnOne -> {
			if (listTwo.contains(userOnOne))
				return userOnOne;
			return null;
		}).filter(user -> user != null).collect(Collectors.toList());
	}
}
