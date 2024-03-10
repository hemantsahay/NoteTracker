package com.learnWithFun.springBoot1.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long Id;

    @NotBlank(message = "Title Can't be Blank")
    @Size(min =1, max = 255, message ="Title should be smaller than 255 characters")
    private String title;

    @NotBlank(message = "Description Can't be Blank")
    @Size(min =1, max = 255,message = "Description should be smaller than 255 characters")
    private String description;

    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public void clone(Note note){
        this.title = note.title;
        this.description = note.description;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
