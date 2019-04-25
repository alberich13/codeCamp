package com.indigo.twiiter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
		return null;
	}
	
	public List<String> getCommonFriends(String userNameA, String userNameB) {
		List<User> listFollowersUserA = this.getFollowersOfUser(userNameA);
		List<User> listFollowersUserB = this.getFollowersOfUser(userNameB);
		List<User> duplicates = this.findDuplicates(listFollowersUserA, listFollowersUserB);
		return duplicates.stream().map(user-> user.getName()).collect(Collectors.toList());
	}
	
	private List<User> getFollowersOfUser(String userName) {
		try {
			PagableResponseList<User> friendList = twitter.getFollowersList(userName, -1);
			int sizeoffreindlist = friendList.size();
			List<User> userList = new ArrayList<>();
			for (int i = 0; i < sizeoffreindlist; i++) {
				userList.add(friendList.get(i));
			}
			return userList;
		} catch (TwitterException e) {
			log.error(e.getLocalizedMessage());
		}
		return null;
	}
	
	private List<User> findDuplicates(List<User> listOne, List<User> listTwo) {
		return listOne.stream().map(userOnOne -> {
			if (listTwo.contains(userOnOne))
				return userOnOne;
			return null;
		}).filter(user -> user != null).collect(Collectors.toList());
	}
}
