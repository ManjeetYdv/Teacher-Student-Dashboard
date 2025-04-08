package com.example.webDproject.Service;


import com.example.webDproject.Entities.CGPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CGPAService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get all CGPA records for a student
    public List<CGPA> getCgpaByRollNumber(String rollNumber) {
        String sql = "SELECT semester, cgpa FROM cgpa WHERE rollNumber = ? ORDER BY semester";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CGPA.class), rollNumber);
    }

    // Insert a new CGPA record
    public void addCgpa(String rollNumber, int semester, double cgpa) {
        String sql = "INSERT INTO cgpa (rollNumber, semester, cgpa) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, rollNumber, semester, cgpa);
    }

    // Optional: Update existing CGPA
    public void updateCgpa(String rollNumber, int semester, double cgpa) {
        String sql = "UPDATE cgpa SET cgpa = ? WHERE rollNumber = ? AND semester = ?";
        jdbcTemplate.update(sql, cgpa, rollNumber, semester);
    }

    // Optional: Check if CGPA entry exists
    public boolean cgpaExists(String rollNumber, int semester) {
        String sql = "SELECT COUNT(*) FROM cgpa WHERE rollNumber = ? AND semester = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, rollNumber, semester);
        return count != null && count > 0;
    }
    // Check if CGPA exists for given roll number and semester
    private boolean exists(String rollNumber, int semester) {
        String sql = "SELECT COUNT(*) FROM cgpa WHERE rollNumber = ? AND semester = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, rollNumber, semester);
        return count != null && count > 0;
    }

    // Update or insert CGPA record
    public void updateOrInsertCgpa(String rollNumber, int semester, double cgpa) {
        if (exists(rollNumber, semester)) {
            String updateSql = "UPDATE cgpa SET cgpa = ? WHERE rollNumber = ? AND semester = ?";
            jdbcTemplate.update(updateSql, cgpa, rollNumber, semester);
        } else {
            String insertSql = "INSERT INTO cgpa (rollNumber, semester, cgpa) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertSql, rollNumber, semester, cgpa);
        }
    }
}
