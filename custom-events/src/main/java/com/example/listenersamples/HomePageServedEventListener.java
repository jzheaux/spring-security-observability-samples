package com.example.listenersamples;

import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

import org.springframework.context.ApplicationListener;
import org.springframework.security.event.KeyValuesObservationEvent;

public class HomePageServedEventListener implements ApplicationListener<HomePageServedEvent> {
	private final ObservationRegistry registry;

	public HomePageServedEventListener(ObservationRegistry registry) {
		this.registry = registry;
	}

	@Override
	public void onApplicationEvent(HomePageServedEvent event) {
		Observation observation = this.registry.getCurrentObservation();
		if (observation == null) {
			return;
		}
		KeyValues kv = KeyValues.of("principal.name", event.getAuthentication().getName());
		Observation.Event observationEvent = Observation.Event.of(event.getEventName());
		observation.event(new KeyValuesObservationEvent(kv, observationEvent));
	}
}
