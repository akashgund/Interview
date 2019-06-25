package com.interview.lucidworks;

import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Security.TokenDecoder;
import com.interview.lucidworks.Service.RegisterUserService;
import com.interview.lucidworks.Util.EmailUtil;
import com.interview.lucidworks.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableRetry
public class LucidworksApplication extends SpringBootServletInitializer {
    static int retryCount=3;
    static int initialDelay=100;



    public static void main(String[] args) {
        SpringApplication.run(LucidworksApplication.class, args);
    }

    @Bean
    public RetryTemplate retryTemplate(){

        RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(retryCount, Collections.singletonMap(RestClientException.class, true)));
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialDelay);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setThrowLastExceptionOnExhausted(true);


       // retryTemplate.registerListener(new DefaultListenerSupport());

        return retryTemplate;


    }



    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> home(HttpServletRequest request, HttpServletResponse response) {

        Map<String,String> map = new HashMap<>();
        map.put("/user/","Menu");
        map.put("/user/register"," API to register new user - Method:PUT input format JSON  -- { \"email\":\"xxxxx@yy.com\",\"password\":\"****\"");
        map.put("Login instruction:","add basic Auth to headers for further use. NOTE: Token lasts for 20 minutes");

        map.put("/note"," API to add new Note Method: PUT input format JSON  -- { \"title\":\"TITLE\",\"content\":\"content here\"");
        map.put("/note","API to view all Notes belonging to the user Method:GET");

        return map;
    }

}