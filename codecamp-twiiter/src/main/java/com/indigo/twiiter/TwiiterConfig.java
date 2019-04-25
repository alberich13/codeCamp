package com.indigo.twiiter;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@ConfigurationProperties("indigo.codecamp")
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TwiiterConfig {
	
	private String consumerKey;
	private String consumerSecret;
	private String consumerToken;
	private String consumerTokenSecret;

	@Bean
	public Twitter twiiterInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerKey)
		  .setOAuthConsumerSecret(consumerSecret)
		  .setOAuthAccessToken(consumerToken)
		  .setOAuthAccessTokenSecret(consumerTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
	
	
}
