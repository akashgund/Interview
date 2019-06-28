package com.interview.lucidworks;

import com.interview.lucidworks.Entity.Note;
import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Security.TokenDecoder;
import com.interview.lucidworks.Service.AccountService;
import com.interview.lucidworks.Service.NoteService;
import com.interview.lucidworks.Service.RegisterUserService;
import com.interview.lucidworks.Util.EmailUtil;
import com.interview.lucidworks.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class EntryController{

    @Autowired
    public RegisterUserService registrationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NoteService noteService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EntryController.class, args);
    }




    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> authenticateUser(HttpServletRequest request, HttpServletResponse response, User account) {

        HashMap<String, String> map = null;
        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;

        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            System.out.println("Email : " + credentials[0] + "Password: " + PasswordUtil.encodePass(credentials[1]));
            User user = accountService.findAccount(credentials[0]);
            if (user != null) {
                map = new HashMap<>();
                if (PasswordUtil.checkPass(credentials[1], user.getPassword())) {
                    DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    map.put("Current Date and Time", dateformat.format(date));

                    return map;
                } else {
                    response.setStatus(401);
                    map.put("Error", "Incorrect Password");
                    return map;
                }
            } else {
                map = new HashMap<>();
                map.put("Error", "You are currently not logged in!");
                response.setStatus(401);
                return map;
            }
        } else {
            map = new HashMap<>();
            map.put("Error", "You are currently not logged in!");
            response.setStatus(400);
            return map;
        }
    }


    @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Map<String, String> registerUser(HttpServletResponse response, @RequestBody User userJSON) throws ServletException, IOException {
        //Convert the request body to JSON Object
        System.out.println("in registration ");

        HashMap<String, String> map = null;

        try {

            String email = userJSON.getEmail();
            String password = userJSON.getPassword();

            if (!EmailUtil.checkEmail(email)) {
                map = new HashMap<>();
                map.put("Error", "Invalid email Format");
                response.setStatus(400);
                return map;
            }
            if (!PasswordUtil.validPass(password)) {
                map = new HashMap<>();
                map.put("Error", "Invalid password Format. Please make sure the password has the following:  1. Atleast 10 characters  2. One uppercase  3. One lowercase  4. One special character");
                response.setStatus(400);
                return map;
            } else {
                String encodedPass = PasswordUtil.encodePass(password);

                User user = new User();
                user.setEmail(email);
                user.setPassword(encodedPass);

                if (!registrationService.createAccount(user)) {
                    map = new HashMap<>();
                    map.put("Error", "Account Already Exists");
                    response.setStatus(400);
                    return map;
                } else {
                    map = new HashMap<>();
                    map.put("Success", "Successfully Registered");
                    return map;
                }
            }


        } catch (Exception e) {
           
            map.put("Error"," Please check input json");
            return map;
            //throw new IOException("Error parsing JSON " + e.getMessage());
        }
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Map<String, Object> createNote(HttpServletRequest request, HttpServletResponse response, @RequestBody Note inputNote) {


        HashMap<String, Object> map = null;
        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;
        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            System.out.println("Email : " + credentials[0] + "Password: " + PasswordUtil.encodePass(credentials[1]));
            User user = accountService.findAccount(credentials[0]);
            if (user != null && PasswordUtil.checkPass(credentials[1], user.getPassword())) {
                try {
                    if (inputNote.getContent() != null && inputNote.getTitle() != null) {
                        Note n = new Note();
                        n.setUser(user);
                        n.setTitle(inputNote.getTitle());
                        n.setContent(inputNote.getContent());

                        n.setUpdated_on();

                        noteService.createNote(n);

                        response.setStatus(201);
                        map = new HashMap<>();
                        n.getUser().setEmail("**********");
                        n.getUser().setPassword("**********");
                        map.put("Note Created: ", n);
                        return map;
                    } else {
                        map = new HashMap<>();
                        map.put("Error ", "Bad request : Empty Note");
                        response.setStatus(400);
                        return map;
                    }
                } catch (Exception e) {
                    map = new HashMap<>();
                    map.put("Error ", "Bad request : Error Creating Note");
                    response.setStatus(400);
                    return map;
                }


            }
        } else {
            map = new HashMap<>();
            map.put("Error ", "You are not logged in!");
            response.setStatus(401);
            return map;
        }
        return null;
    }

}