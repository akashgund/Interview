package com.interview.lucidworks;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.annotation.EnableRetry;

import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.*;
import java.io.IOException;


@EnableRetry
@SpringBootTest(classes={AccountService.class})
public class  AccountServiceTest {


   
    @Autowired
    private AccountService accountService;


    @Test
    public void accountTest()throws IOException{
        
       User user= new User();

       user.setEmail("xxxyyy@gmail.com");
       user.setPassword("123456");

        try{
        assertNull(accountService.findAccount(user.getEmail()));
        }
        catch(Exception e){
            System.out.println("Internal error occured");
        }
    }

}