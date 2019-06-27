package com.interview.lucidworks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.retry.annotation.EnableRetry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interview.lucidworks.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.*;
import java.io.IOException;


@EnableRetry
@SpringBootTest(classes={WeatherUpdateController.class})
public class  WeatherUpdateControllerTest {


    @Autowired
    private WeatherUpdateController weatherUpdateController;
    @Autowired
    private AccountService accountService;


    @Test
    public void retryableTest()throws IOException{

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        try{
        assertNotNull(weatherUpdateController.home(request,response));
        }
        catch(Exception e){
            System.out.println("Internal error occured");
        }
    }

}