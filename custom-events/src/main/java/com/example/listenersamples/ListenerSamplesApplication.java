package com.example.listenersamples;

import io.micrometer.observation.ObservationRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.event.DelegatingObservationSecurityEventListener;
import org.springframework.security.event.SecurityEvent;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class ListenerSamplesApplication {

	@Bean
	ApplicationListener<SecurityEvent> securityEvents(ObservationRegistry registry) {
		return DelegatingObservationSecurityEventListener.withDefaults(registry)
				.add(HomePageServedEvent.class, new HomePageServedEventListener(registry))
				.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.authorities("app")
						.build(),
				User.withDefaultPasswordEncoder()
						.username("unauthorized")
						.password("password")
						.authorities("user")
						.build()
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(ListenerSamplesApplication.class, args);
	}

}
