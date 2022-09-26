package com.example.listenerreactivesamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
public class ListenerReactiveSamplesApplication {

	@Bean
	public ReactiveUserDetailsService userDetailsService() {
		return new MapReactiveUserDetailsService(
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.authorities("app")
						.build()
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(ListenerReactiveSamplesApplication.class, args);
	}

}
