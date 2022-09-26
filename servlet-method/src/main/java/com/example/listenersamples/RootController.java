package com.example.listenersamples;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping("/")
	@PreAuthorize("authenticated")
	public String home(Authentication authentication) {
		return "Welcome home, " + authentication.getName();
	}

}
