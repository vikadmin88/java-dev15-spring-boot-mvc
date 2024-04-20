package org.springtest.repository;

import lombok.extern.slf4j.Slf4j;
import org.springtest.entity.Note;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class NoteRepository {

    private final List<Note> notes = new ArrayList<>();
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NoteRepository.class);

    public List<Note> findAllNotes() {
        LOGGER.info("Find all notes");
        return notes;
    }
    public Note addNote(Note note) {
        LOGGER.info("Add note: {}", note);
        note.setId(getRandomId());
        notes.add(note);
        return note;
    }

    public void removeNoteById(long id) {
        LOGGER.info("Remove note by id: {}", id);
        Note note = findNoteById(id);
        if (note != null) {
            notes.remove(note);
        } else {
            throw new IllegalArgumentException("Note not found");
        }
    }

    public void updateNote(Note note) {
        LOGGER.info("Update note: {}", note);
        Note noteToUpdate = findNoteById(note.getId());
        if (noteToUpdate != null) {
            int noteId = notes.indexOf(noteToUpdate);
            notes.set(noteId, note);
        } else {
            throw new IllegalArgumentException("Note not found");
        }
    }

    public Note findNoteById(long id) {
        LOGGER.info("Find note by id: {}", id);
        return notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private long getRandomId() {
        long rndLong = new Random().nextLong();
        return rndLong < 0 ? -rndLong : rndLong;
    }
}
