package com.interview.lucidworks.Service;
import com.interview.lucidworks.Entity.Note;
import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@ComponentScan("com.interview.lucidworks.Service")
public class NoteService {

    @Autowired
    NoteRepository noteRepo;

    public boolean createNote(Note note) {
        try {
            noteRepo.save(note);
            return true;
        } catch (Exception ex) {
            System.out.println("Error creating note: " + ex.getMessage());
        }
        return false;
    }

    public Note findNote(String id) {
        Optional<Note> note = noteRepo.findById(id);
        if (note.isPresent()) {
            Note foundNote = note.get();
            return foundNote;
        }
        return null;
    }

    public ArrayList<Note> getUserNotes(User user) {
        ArrayList<Note> notes = new ArrayList<>();
        for (Note note : noteRepo.findAll()) {
            if (user.getUserId() == note.getUser().getUserId()) {
                notes.add(note);
            }
        }
        return notes;
    }

    public boolean deleteNote(Note note) {
        try {
            noteRepo.delete(note);
            return true;
        } catch (Exception ex) {
            System.out.print("Failed to delete Note " + ex.getMessage());
            return false;
        }
    }

    public boolean updateNote(Note note) {
        try {
            noteRepo.save(note);
            return true;
        } catch (DataAccessException ex) {
            System.out.print("Failed to Update Note " + ex.getMessage());
        }
        return false;
    }
}
