package com.bhuvancom.aws_user_table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "notes")
public class User {
    private String userId;

    private Integer noteId;

    private String note;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", noteId=" + noteId +
                ", note='" + note + '\'' +
                '}';
    }

    public User() {
    }

    public User(String userId, Integer noteId, String note) {
        this.userId = userId;
        this.noteId = noteId;
        this.note = note;
    }

    @DynamoDBHashKey(attributeName = "UserId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "NotesId")
    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    @DynamoDBAttribute(attributeName = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
