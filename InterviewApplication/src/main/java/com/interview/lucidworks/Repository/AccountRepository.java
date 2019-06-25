package com.interview.lucidworks.Repository;
import com.interview.lucidworks.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<User, Integer> {

}