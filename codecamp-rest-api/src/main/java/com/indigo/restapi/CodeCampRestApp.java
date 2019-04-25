package com.indigo.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;
import com.indigo.twiiter.TwiiterConfig;

@SpringBootApplication
@Import({ TwiiterConfig.class, RestConfig.class})
public class CodeCampRestApp {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CodeCampRestApp.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}
}