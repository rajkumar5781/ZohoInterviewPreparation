package com.applications.railway_reservation;

import java.util.ArrayList;

public class PersonInfo {
    private String name;
    private int age;
    private char preferance;
    private String gender;
    private int childrenCount = 2;
    private ArrayList<PersonInfo> children;

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getPreferance() {
        return preferance;
    }

    public void setPreferance(char preferance) {
        this.preferance = preferance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<PersonInfo> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<PersonInfo> children) {
        this.children = children;
    }

    public PersonInfo(String name, int age, char preferance, String gender) {
        this.name = name;
        this.age = age;
        this.preferance = preferance;
        this.gender = gender;
        if(age>5){
            this.children = new ArrayList<>();
        }
    }
}
