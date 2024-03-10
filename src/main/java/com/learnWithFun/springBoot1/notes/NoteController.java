package com.learnWithFun.springBoot1.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    NoteRepo noteRepo;

    @PostMapping("")
    public ResponseEntity<String> addNotes(@Valid @RequestBody Note note, BindingResult result){
        if(result.hasErrors()){
            String ErrorMSg = result.getFieldErrors().toString();
            return new ResponseEntity<>("ErrorMSg",HttpStatus.BAD_REQUEST);
        }
       Note newNote = noteRepo.save(note);
        return new ResponseEntity<>("Note Created Successfully with Id: "+newNote.getId(), HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Note> getNotes(){
        return noteRepo.findAll();
    }

    @GetMapping("/{id}")
    public Note getNotes(@PathVariable Long id){
        return noteRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id){
        try {
            noteRepo.deleteById(id);
            return new ResponseEntity<>("Note with Id: "+id+" deleted successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNote(@Valid @RequestBody Note note, @PathVariable Long id){
        Note  existingNote = noteRepo.findById(id).orElse(null);
        if(existingNote==null)
            return new ResponseEntity<>("Note with given Id does not exists", HttpStatus.BAD_REQUEST);
        existingNote.clone(note);
        noteRepo.save(existingNote);
        return new ResponseEntity<>("Note Updated Successfully",  HttpStatus.OK);
    }

    private Boolean isBlank(String s){
        return "".equals(s);
    }
}
