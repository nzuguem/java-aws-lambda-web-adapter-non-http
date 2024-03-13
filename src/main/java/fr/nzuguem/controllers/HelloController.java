package fr.nzuguem.controllers;


import fr.nzuguem.models.ScheduledEvent;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("hello")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Post("events")
    public HttpResponse<String> helloEb(@Body ScheduledEvent event) {

        LOGGER.info("Received Events : {}", event);

        return HttpResponse.ok("success");
    }

}