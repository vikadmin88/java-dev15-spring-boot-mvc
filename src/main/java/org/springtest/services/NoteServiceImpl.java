package org.springtest.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springtest.entity.Note;
import org.springtest.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public List<Note> listAll() {
        LOGGER.info("Getting all notes from repository");
        return noteRepository.findAllNotes();
    }

    @Override
    public Note add(Note note) {
        LOGGER.info("Adding note: {}", note);
        return noteRepository.addNote(note);
    }

    @Override
    public void deleteById(long id) {
        LOGGER.info("Deleting note with id: {}", id);
        noteRepository.removeNoteById(id);
    }

    @Override
    public void update(Note note) {
        LOGGER.info("Updating note: {}", note);
        noteRepository.updateNote(note);
    }

    @Override
    public Note getById(long id) {
        LOGGER.info("Getting note with id: {}", id);
        Note note = noteRepository.findNoteById(id);
        if (note != null) return note;
        throw new IllegalArgumentException("Note not found");
    }
}
