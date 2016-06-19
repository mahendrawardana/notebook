package com.mahendrawardana.notebook;

/**
 * Created by Mahendra Wardana Z on 6/14/2016.
 */

public class Note {

    private String title, message;
    private long noteId, dateCreatedMili;
    private Category category;

    public enum Category { FACEBOOK, INSTAGRAM, PLUS, TWITTER }


    // Setter
    public Note(String title, String message, Category category) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMili = 0;
    }

    /*public Note(String title, String message, Category category, long noteId, long dateCreatedMili) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMili = dateCreatedMili;
    }*/


    // Getter
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public Category getCategory() { return category; }
    public long getDate() { return dateCreatedMili; }
    public long getId() { return noteId; }
    public int getAssociateDrawable() {
        return categoryToDrawable(category);
    }


    public String toString() {
        return "ID: "+noteId+" Title: "+title+" Message: "+message+" IconID: "+category.name()+ " Date: "+dateCreatedMili;
    }
    public static int categoryToDrawable(Category noteCategory) {
        switch(noteCategory) {
            case FACEBOOK: return R.drawable.facebook;
            case INSTAGRAM: return R.drawable.instagram;
            case PLUS: return R.drawable.plus;
            case TWITTER: return R.drawable.twitter;
        }
        return  R.drawable.facebook;
    }

}
