package com.example.listenersamples;

import org.springframework.security.core.Authentication;
import org.springframework.security.event.SecurityEvent;

public class HomePageServedEvent extends SecurityEvent {
	public HomePageServedEvent(Authentication authentication) {
		super(authentication);
	}

	public Authentication getAuthentication() {
		return (Authentication) super.getSource();
	}
}
