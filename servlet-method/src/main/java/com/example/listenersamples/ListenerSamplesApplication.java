package com.example.listenersamples;

import io.micrometer.observation.ObservationRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.ObservationAuthorizationDeniedEventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.ListeningSecurityContextHolderStrategy;
import org.springframework.security.core.context.ObservationSecurityContextChangedEventListener;
import org.springframework.security.core.context.SecurityContextChangedEvent;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.event.DelegatingObservationSecurityEventListener;
import org.springframework.security.event.SecurityEvent;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@SpringBootApplication
@EnableMethodSecurity
public class ListenerSamplesApplication {

	@Bean
	SecurityContextHolderStrategy securityContextHolderStrategy(ApplicationEventPublisher publisher) {
		return new ListeningSecurityContextHolderStrategy(publisher::publishEvent);
	}

	@Bean
	HttpSessionEventPublisher eventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	ApplicationListener<SecurityEvent> securityEvents(ObservationRegistry registry) {
		return DelegatingObservationSecurityEventListener.withDefaults(registry)
				.add(SecurityContextChangedEvent.class, new ObservationSecurityContextChangedEventListener(registry))
				.add(AuthorizationDeniedEvent.class, new ObservationAuthorizationDeniedEventListener(registry))
				.build();
	}

	@Bean
	SecurityFilterChain http(HttpSecurity http) throws Exception {
		return http
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.authorities("app")
						.build()
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(ListenerSamplesApplication.class, args);
	}

}
