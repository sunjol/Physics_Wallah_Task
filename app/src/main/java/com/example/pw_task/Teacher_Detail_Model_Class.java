package com.example.pw_task;

import java.util.ArrayList;
import java.util.List;

public class Teacher_Detail_Model_Class {

    //Class that stores Incoming data

    private int id;
    private String name;
    private String profileImage;
    private String qualification;
    private String subjects;


    public Teacher_Detail_Model_Class() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }


    
}
