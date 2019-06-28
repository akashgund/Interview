package com.interview.lucidworks;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.annotation.EnableRetry;
import com.interview.lucidworks.Service.AccountService;
import com.interview.lucidworks.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.*;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestBody;


@EnableRetry
@SpringBootTest(classes={EntryController.class})
public class  EntryControllerTest {


    @Autowired
    private EntryController enrtyController;
    @Autowired
    private AccountService accountService;


    @Test
    public void homeTest()throws IOException{

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User();
        try{
            

        assertNotNull(enrtyController.authenticateUser(request,response,user));
        }
        catch(Exception e){
            System.out.println("Internal error occured");
        }

    }

}