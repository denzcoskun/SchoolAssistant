package com.denzcoskun.schoolassistant.screens.notes.models;

/**
 * Created by Denx on 18.06.2018.
 */
public class NoteModel {
    private String noteName;
    private String noteText;

    public NoteModel(String noteName, String noteText) {
        this.noteName = noteName;
        this.noteText = noteText;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
