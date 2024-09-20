package io.github.muhammadredin.securenotesbackend.note.controller;

import io.github.muhammadredin.securenotesbackend.note.services.impl.NoteServiceImpl;
import io.github.muhammadredin.securenotesbackend.note.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    private NoteServiceImpl noteService;

    @PostMapping
    public Note addNoteHandler(@RequestBody String content,
                               @AuthenticationPrincipal UserDetails user
    ) {
        String username = user.getUsername();
        return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getAllNotesHandler(@AuthenticationPrincipal UserDetails user) {
        return noteService.getNotesForUser(user.getUsername());
    }

    @PutMapping("/{noteId}")
    public Note updateNoteHandler(@PathVariable Long noteId,
                                  @RequestBody String content,
                                  @AuthenticationPrincipal UserDetails user
    ) {
        return noteService.updateNoteForUser(noteId, content, user.getUsername());
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteHandler(@PathVariable Long noteId,
                                  @AuthenticationPrincipal UserDetails user) {
        noteService.deleteNoteForUser(noteId, user.getUsername());
    }
}
