package io.github.muhammadredin.securenotesbackend.note.services.impl;

import io.github.muhammadredin.securenotesbackend.note.models.Note;
import io.github.muhammadredin.securenotesbackend.note.repositories.NoteRepository;
import io.github.muhammadredin.securenotesbackend.note.services.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        if (note.getOwnerUsername().equals(username)) {
            noteRepository.deleteById(noteId);
        }
        throw new RuntimeException("Can't authenticate the owner of the note");
    }

    @Transactional
    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository
                .findAllByOwnerUsername(username);
        return personalNotes;
    }
}
