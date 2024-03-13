package fr.nzuguem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("hello")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    private final  ObjectMapper objectMapper;

    public HelloController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Post("events")
    public HttpResponse<String> hello(@Body String body) throws JsonProcessingException {

        var jsonNode = objectMapper.readTree(body);

        LOGGER.info("Received Events : {}", jsonNode.toPrettyString());

        return HttpResponse.ok("success");
    }

}