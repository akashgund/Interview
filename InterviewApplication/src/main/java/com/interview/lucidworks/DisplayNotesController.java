package com.interview.lucidworks;

import com.interview.lucidworks.Entity.Note;
import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Security.TokenDecoder;
import com.interview.lucidworks.Service.AccountService;
import com.interview.lucidworks.Service.NoteService;
import com.interview.lucidworks.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@RestController
@EnableAutoConfiguration
public class DisplayNotesController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private NoteService noteService;






    /*@Autowired
    private FileDownloadService fileDownloadService;*/

    @RequestMapping(value = "/note", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getAllNotesForUser(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = null;
        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;

        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            User currentUser = accountService.findAccount(credentials[0]);
            System.out.println(currentUser.getEmail()+"   "+credentials[1]+"   "+currentUser.getPassword());
            PasswordUtil.checkPass(credentials[1], currentUser.getPassword());
            if (currentUser != null && PasswordUtil.verifyUserPassword(credentials[1], currentUser.getPassword())) {
                ArrayList<Note> noteList = null;
                noteList = noteService.getUserNotes(currentUser);


                map = new HashMap<>();
                if (noteList.isEmpty()) {
                    map.put("NotesList", "There are currently no notes for this user");
                    response.setStatus(200);
                    return map;
                } else {
                    for (Note n : noteList) {
                        n.getUser().setEmail("**********");
                        n.getUser().setPassword("**********");

                    }

                    map.put("NotesList", noteList);
                    response.setStatus(200);

                }
            } else {
                map = new HashMap<>();
                map.put("Error", "Invalid User!");
                response.setStatus(400);
                return map;
            }
        } else {
            map = new HashMap<>();
            map.put("Error", "You are currently not logged in!");
            response.setStatus(401);
            return map;
        }
        return map;
    }
}
