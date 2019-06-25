package com.interview.lucidworks.Repository;

import com.interview.lucidworks.Entity.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, String> {
}
