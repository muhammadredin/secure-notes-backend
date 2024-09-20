package io.github.muhammadredin.securenotesbackend.note.repositories;

import io.github.muhammadredin.securenotesbackend.note.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOwnerUsername(String username);
}
