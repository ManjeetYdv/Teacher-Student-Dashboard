package com.example.webDproject.Entities;

public class CGPA {
    private int semester;
    private double cgpa;

    public CGPA() {
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public CGPA(int semester, double cgpa) {
        this.semester = semester;
        this.cgpa = cgpa;
    }
}
