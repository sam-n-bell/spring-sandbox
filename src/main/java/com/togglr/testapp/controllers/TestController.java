package com.togglr.testapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {

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

}
