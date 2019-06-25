package com.interview.lucidworks.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.lucidworks.Util.UUIDGenerator;

@Entity
@Table(name="Attachments")
public class Attachment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String attachmentId;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UserId", referencedColumnName = "UserId", foreignKey = @ForeignKey(name = "UserId"))
    User user;

    @Column(name="url")
    String url;


    public Attachment(){
        attachmentId = UUIDGenerator.getUUID();
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String id) {
        this.attachmentId = id;
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}