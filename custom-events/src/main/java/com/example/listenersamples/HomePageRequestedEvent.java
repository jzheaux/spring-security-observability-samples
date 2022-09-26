package com.example.listenersamples;

import java.nio.file.AccessDeniedException;

import org.springframework.security.core.Authentication;
import org.springframework.security.event.EventFailureAccessor;
import org.springframework.security.event.SecurityEvent;

public class HomePageRequestedEvent extends SecurityEvent implements EventFailureAccessor<AccessDeniedException> {
	private AccessDeniedException error;

	public HomePageRequestedEvent(Authentication authentication) {
		super(authentication);
	}

	public Authentication getAuthentication() {
		return (Authentication) super.getSource();
	}

	@Override
	public AccessDeniedException getError() {
		return error;
	}

	public void setError(AccessDeniedException error) {
		this.error = error;
	}
}
