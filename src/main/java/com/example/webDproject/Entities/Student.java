package com.example.webDproject.Entities;

public class Student extends User {
    private String name;
    private String rollNumber;
    private String password;
    private String gmail;
    private String course;
    private double cgpa;
    public Student(String name, String rollNumber, String password, double cgpa, String course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.password = password;
        this.cgpa = cgpa;
        this.course = course;
    }

    public Student(String name, String rollNumber, String password, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.password = password;
        this.gmail = email;
    }

    public String getEmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", password='" + password + '\'' +
                ", course='" + course + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }



    // Constructors, Getters, Setters
}
