package com.interview.lucidworks.Service;
import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.lucidworks.Service")
public class RegisterUserService {

    @Autowired
    AccountRepository repository;




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



}