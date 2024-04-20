package org.springtest;

import org.springtest.entity.Note;
import org.springtest.repository.NoteRepository;
import org.springtest.services.NoteService;
import org.springtest.services.NoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NoteServiceImplTests {

    private Note note;
    private NoteService noteService;

    @BeforeEach
    public void beforeEach() {
        NoteRepository noteRepository = new NoteRepository();
        noteService = new NoteServiceImpl(noteRepository);
        note = new Note();
    }

    @Test
    void testAddNote() {
        //When
        Note noteObj = noteService.add(note);

        //Then
        int expected = 0;
        Assertions.assertTrue(noteObj.getId() != expected);
    }

    @Test
    void testGetById() {
        //When
        note.setTitle("TEST");
        Note noteAdded = noteService.add(note);
        Note noteObj = noteService.getById(noteAdded.getId());

        //Then
        String  expected = "TEST";
        Assertions.assertEquals(expected, noteObj.getTitle());
    }

    @Test
    void testUpdate() {
        //When
        Note noteAdded = noteService.add(note);
        noteAdded.setTitle("UPDATED");
        noteService.update(noteAdded);
        Note noteObj = noteService.getById(noteAdded.getId());

        //Then
        String expected = "UPDATED";
        Assertions.assertEquals(expected, noteObj.getTitle());
    }

    @Test
    void testDelete() {
        //When
        Note noteAdded = noteService.add(note);
        List<Note> listNotes = noteService.listAll();
        int expected = 1;
        Assertions.assertEquals(expected, listNotes.size());

        noteService.deleteById(noteAdded.getId());

        //Then
        int expected2 = 0;
        Assertions.assertEquals(expected2, listNotes.size());
    }

    @Test
    void testListAll() {
        //When
        noteService.add(note);
        List<Note> listNotes = noteService.listAll();

        //Then
        int expected = 1;
        Assertions.assertEquals(expected, listNotes.size());
    }

    @Test
    void testUpdateIllegalArgumentException() {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            noteService.update(note);
        });
    }

    @Test
    void testDeleteIllegalArgumentException() {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            noteService.deleteById(note.getId());
        });
    }

    @Test
    void testGetByIdIllegalArgumentException() {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            noteService.getById(0);
        });
    }

}
