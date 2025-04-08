package com.example.webDproject.Entities;
import java.util.*;
public class StudentDashboardDTO {
    private String name;
    private String rollNumber;
    private String gmail;
    private String course;
    private List<CGPA> cgpaList;
    private double cgpaScore;

    public StudentDashboardDTO() {
    }

    public StudentDashboardDTO(String name, String rollNumber, String course, String gmail, List<CGPA> cgpaList, double cgpaScore) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
        this.gmail = gmail;
        this.cgpaList = cgpaList;
        this.cgpaScore = cgpaScore;
    }
    public StudentDashboardDTO(String name, String rollNumber, String course, String gmail, List<CGPA> cgpaList) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
        this.gmail = gmail;
        this.cgpaList = cgpaList;
    }

    public String getName() {
        return name;
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<CGPA> getCgpaList() {
        return cgpaList;
    }

    public void setCgpaList(List<CGPA> cgpaList) {
        this.cgpaList = cgpaList;
    }

    public double getCgpaScore() {
        return cgpaScore;
    }

    public void setCgpaScore(double cgpaScore) {
        this.cgpaScore = cgpaScore;
    }
}
