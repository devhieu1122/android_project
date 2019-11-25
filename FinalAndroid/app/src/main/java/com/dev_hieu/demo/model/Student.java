package com.dev_hieu.demo.model;

import androidx.annotation.NonNull;

public class Student {
    int code;
    String classID;
    String name;
    String email;
    String phone;
    String gender;
    String birth;
    String address;
    String note;

    public Student() {
    }

    public Student(int code, String classID, String name, String email, String phone, String gender, String birth, String address, String note) {
        this.code = code;
        this.classID = classID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.note = note;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @NonNull
    @Override
    public String toString() {
        String str = "Student [ code: " + code + "\n"
                + "classID: " + classID + "\n"
                + "name:    " + name + "\n"
                + "email:   " + email + "\n"
                + "phone:   " + phone + "\n"
                + "gender:  " + gender + "\n"
                + "birth:   " + birth + "\n"
                + "address: " + address + "\n"
                + "note:    " + note + " ]\n";
        return str;
    }
}
