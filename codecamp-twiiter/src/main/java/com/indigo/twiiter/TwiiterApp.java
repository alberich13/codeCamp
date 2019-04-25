package com.indigo.twiiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({TwiiterConfig.class})
public class TwiiterApp {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(TwiiterApp.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}
}
