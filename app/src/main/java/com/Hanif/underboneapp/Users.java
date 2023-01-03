package com.Hanif.underboneapp;

import com.google.firebase.firestore.Exclude;

public class Users {
    private String name;
    private String Email;
    //private String phoneNum;
    private String documentId;

    public Users(String name, String email) {
        this.name = name;
        Email = email;
       // this.phoneNum = phoneNum;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

//    public String getPhoneNum() {
//        return phoneNum;
//    }
//
//    public void setPhoneNum(String phoneNum) {
//        this.phoneNum = phoneNum;
//    }
}
