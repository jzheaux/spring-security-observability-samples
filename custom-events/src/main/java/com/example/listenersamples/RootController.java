package com.example.listenersamples;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@Autowired
	ApplicationEventPublisher publisher;

	@GetMapping("/")
	public String home(Authentication authentication) {
		HomePageRequestedEvent event = new HomePageRequestedEvent(authentication);
		if ("unauthorized".equals(authentication.getName())) {
			event.setError(new AccessDeniedException("denied home page"));
		}
		this.publisher.publishEvent(event);
		String page = "Welcome home, " + authentication.getName();
		this.publisher.publishEvent(new HomePageServedEvent(authentication));
		return page;
	}

}
