package com.interview.lucidworks.Repository;

import org.springframework.data.repository.CrudRepository;
import com.interview.lucidworks.Entity.Token;

public interface TokenRepository extends CrudRepository<Token, String> {

}
