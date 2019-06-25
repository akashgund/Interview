package com.interview.lucidworks.Service;


import com.interview.lucidworks.Entity.Token;
import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Repository.AccountRepository;
import com.interview.lucidworks.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.cloud.NotepadApp.Service")
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    TokenRepository tokRepo;

    public boolean createAccount(User account) {
        System.out.println("Account " + account.getEmail());

        if (!isExisting(account)) {
            try {
                repository.save(account);
                return true;
            } catch (DataAccessException ex) {
                System.out.print("Failed to create Account. " + ex.getMessage());
            }
        }
        return false;
    }
    @Retryable(value={Exception.class} , maxAttempts = 3)
    public Boolean isExisting(User account) {
        try {
            for (User user : repository.findAll()) {
                if (user.getEmail().equalsIgnoreCase(account.getEmail())) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        return false;
    }

    @Recover
    public String recover(Exception e){
        return (" User already exists exception"+ e.getMessage());

    }

    public User findAccount(String email) {
        for (User user : repository.findAll()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateAccount(User account) {
        try {
            repository.save(account);
            return true;
        } catch (DataAccessException ex) {
            System.out.print("Failed to Update Account " + ex.getMessage());
        }
        return false;
    }

    public void Addtoken(Token token) {

        try {
            tokRepo.save(token);
        } catch (Exception e) {
            System.out.print("Failed to create token. " + e.getMessage());
        }

    }

    public Token fetchToken(Token token) {
        try {
            for (Token t : tokRepo.findAll()) {
                if (t.getToken().equals(token.getToken())) {
                    return t;
                }
            }
            return null;
        } catch (Exception ex) {
            System.out.print("Failed to get Token. " + ex.getMessage());
        }
        return null;
    }

}
