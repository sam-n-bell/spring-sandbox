package com.togglr.testapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.togglr.testapp.entities.ApplicationUserEntity;
import com.togglr.testapp.entities.TaskEntity;
import com.togglr.testapp.repositories.TaskRepository;
import com.togglr.testapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationUserController {

    private String ssourl;
    private String clientSecret = "d0c0440a74e5e301e87d78d344d09e07de92c092";
    private String clientId = "Iv1.6b40905717dc97ea";
    private String accessTokenUri = "https://github.com/login/oauth/access_token";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}/details")
    public ResponseEntity<?> returnUserDetails(@PathVariable("id") int id, Sort sort) {
        ApplicationUserEntity appUser = userRepository.findById(id);
        List<TaskEntity> userTasks = taskRepository.findAllByUserId(id, sort);
        appUser.setTasksById(userTasks);
        return ResponseEntity.ok(appUser);
    }

    @RequestMapping(method = RequestMethod.PATCH, path="/users/{id}/recover")
    public ResponseEntity<?> recoverUser(@PathVariable("id") int id) {
        ApplicationUserEntity applicationUserEntity = userRepository.findDeletedEntityById(id);
        applicationUserEntity.setDeleted(false);
        userRepository.save(applicationUserEntity);
        return ResponseEntity.ok(applicationUserEntity);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/testing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testPost(@RequestParam String code, @RequestHeader Map<String, String> headers) {
        System.out.println(code);
        headers.forEach((key, value) -> {
           System.out.println(String.format("Header '%s' = %s", key, value));
        });
        return ResponseEntity.ok("{\"access_token\": 1029846, \"token_type\": \"bearer\"}"); // github returns a response like this one when POSTing to get access token
    }

    @RequestMapping(method = RequestMethod.GET, value="/togglr-api/oauth/signin/callback")
    @ResponseBody
    public ResponseEntity<?> handleCode(@RequestParam(defaultValue = "None") String code) {
        if (!code.equalsIgnoreCase("None")) {
            StringBuilder accessTokenRequestUri = new StringBuilder();
            accessTokenRequestUri.append(this.accessTokenUri)
                    .append("?client_id=").append(this.clientId)
                    .append("&client_secret=").append(this.clientSecret)
                    .append("&code=").append(code);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            // below this is test code to see how to make POST requests from Spring and then how to parse a JSON response
            System.out.println(accessTokenRequestUri.toString());
            ResponseEntity<String> response = this.restTemplate.postForEntity(
                    "https://797c2354.ngrok.io/testing?code=7864", // this is actually localhost:8080, i was just using a tunneling service to get around potential CORS issue (may not happen). The endpoint is above this function
                    entity,
                    String.class);
            System.out.println(response.getStatusCodeValue());

            ObjectMapper objectMapper = new ObjectMapper();
            String token;

            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                token = jsonNode.get("access_token").asText(null);
            } catch (Exception e) {
                token = null;
            }

            System.out.println("token = " + token);
            // above is test code to see how to make POST requests from Spring and then how to parse a JSON response


        }

        return ResponseEntity.ok(code.toString());
    }

//    https://github.com/login/oauth/authorize?client_id=Iv1.6b40905717dc97ea&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftogglr-api%2Foauth%2Fsignin%2Fcallback
//    https://github.com/login/oauth/access_token?client_id=Iv1.6b40905717dc97ea&client_secret=d0c0440a74e5e301e87d78d344d09e07de92c092&code=d009fac6cfc52ce24d19


}
