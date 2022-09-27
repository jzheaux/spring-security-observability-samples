Spring Security Observability Samples
-------------------------------------

Run these samples see Spring Security's intregration with Micrometer in action.

They are based off of Spring Security defaults, so in each case you can either go to http://localhost:8080 in the browser or to localhost:8080 from the command line.

An easy way to see the metrics is to get Zipkin running.

And an easy way to do that is using [Jonathan Ivanov's docker compose configuration](https://github.com/jonatan-ivanov/local-services/blob/main/zipkin/docker-compose.yml).

Once both the sample app and Zipkin is up and running, use the application and then browse to http://localhost:9411 where you can search for and see Spring Security timings and events.
