package com.example.mynotes.model;

import io.realm.RealmObject;

public class Notice extends RealmObject {

    String title  , note ;
    //password ;


    public  String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   /* public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  */

    @Override
    public String toString() {
        return "Notice{" +
                "title='" + title + '\'' +
                ", note='" + note + '\'' +
                //", password='" + password + '\'' +
                '\n';
    }

}
