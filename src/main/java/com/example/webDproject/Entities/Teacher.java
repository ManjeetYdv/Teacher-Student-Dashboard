package com.example.webDproject.Entities;



public class Teacher extends User {
    private String name;
    private String gmail;
    private String password;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", gmail='" + gmail + '\'' +
                ", password='" + password + '\'' +
                ", courses='" + courses + '\'' +
                ", experience=" + experience +
                '}';
    }

    public Teacher(String name, String gmail, String password, String department) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.department = department;
    }

    public Teacher() {

    }



    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher(String name, String gmail, String password, String courses, int experience) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.courses = courses;
        this.experience = experience;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    private String courses;      // You can make this List<String> if you're storing multiple courses
    private int experience;      // in years

}
