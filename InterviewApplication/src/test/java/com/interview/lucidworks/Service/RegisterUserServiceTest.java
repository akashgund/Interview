package com.interview.lucidworks.Service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.annotation.EnableRetry;

import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import java.io.IOException;
import com.interview.lucidworks.Service.*;


@EnableRetry
@SpringBootTest(classes={AccountService.class})
public class  RegisterUserServiceTest {


   

    @Autowired
    private RegisterUserService registerService;


    @Test
    public void accountTest()throws IOException{
        User user = new User();
       user.setEmail("xxx@gmail.com");
       user.setPassword("Northeastern@1234");
       
        try{
        assertTrue(registerService.createAccount(user));
        }
        catch(Exception e){
            System.out.println("Internal error occured");
        }


        try{
            assertTrue(registerService.isExisting(user));
            }
            catch(Exception e){
                System.out.println("Internal error occured");
            }

            try{
                assertFalse(registerService.isExisting(user));
                }
                catch(Exception e){
                    System.out.println("Internal error occured");
                }    
    }

}