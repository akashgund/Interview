package com.interview.lucidworks.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.lucidworks.Util.UUIDGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "Note")
public class Note {

    @Id
    @Column(name = "noteId")
    String noteId;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", foreignKey = @ForeignKey(name = "OwnerId"))
    User user;

    @Column(name = "content")
    String content;

    @Column(name = "title")
    String title;

    @Column(name = "created_on")
    Date created_on;

    @Column(name = "updated_on")
    Date updated_on;




    //UniqueIdGenerator uig = null;

    public Note() {
        //uig = new UniqueIdGenerator();
        noteId = UUIDGenerator.getUUID();
        created_on = new Date();
    }

    public String getNoteId() {
        return noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_on() {
        return String.valueOf(updated_on);
    }

    public void setUpdated_on() {
        updated_on = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on() {
        this.created_on = new Date();
    }


}
