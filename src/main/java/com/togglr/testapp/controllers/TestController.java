package com.togglr.testapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.togglr.testapp.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET, value= "/test/destination")
    public String destination() {
        return "Destination reached";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test/redirect")
    public void redirectTest(@RequestParam(defaultValue = "http://localhost:8080/test/destination", required = false) String redirectUrl, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("TEST-TOKEN", "abcd");
        cookie.setMaxAge(1000);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect(redirectUrl);
    }

    @RequestMapping(method = RequestMethod.GET, value="/togglr-api/oauth/user")
    @ResponseBody
    public void testGetUser(@RequestParam(defaultValue = "None") String accessToken, HttpServletResponse response ) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> userResponse = this.restTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                entity,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String name;
        JsonNode jsonNode = objectMapper.readTree(userResponse.getBody());
        name = jsonNode.get("login").asText(null);
        System.out.println(name);

        User userDetails = new User(name, "",  true, true, true, true, new ArrayList<>());
        System.out.println(userDetails.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);

        Cookie oauthCookie = new Cookie("OAUTH-TOKEN", accessToken);
        oauthCookie.setMaxAge(10000);
        oauthCookie.setDomain("localhost");
        oauthCookie.setPath("/");
        Cookie togglrCookie = new Cookie("X-TOGGLR-TOKEN", jwt);
        togglrCookie.setMaxAge(10000);
        togglrCookie.setDomain("localhost");
        togglrCookie.setPath("/");
        response.addCookie(oauthCookie);
        response.addCookie(togglrCookie);
        response.sendRedirect("http://localhost:8080/test/twocookies");
    }

    @RequestMapping(method = RequestMethod.GET, value="/test/twocookies")
    @ResponseBody
    public String testTwoTokens(){
        return "Check cookies";
    }

    @RequestMapping(method = RequestMethod.POST, value="/test/webhook")
    @ResponseBody
    public String webHookEndpoint() {
        try {
            System.out.println("sleeping at " + new Date().toString());
            TimeUnit.MINUTES.sleep(3L);
            System.out.println("resuming at " + new Date().toString());
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("from exception " + new Date().toString());
            return null;
        }
    }
}
