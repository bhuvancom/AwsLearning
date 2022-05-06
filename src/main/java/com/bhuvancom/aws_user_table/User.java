package com.bhuvancom.aws_user_table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "notes")
public class User {
    @DynamoDBHashKey(attributeName = "UserId")
    private String userId;
    @DynamoDBRangeKey(attributeName = "NotesId")
    private Integer noteId;
    @DynamoDBAttribute(attributeName = "Note")
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
