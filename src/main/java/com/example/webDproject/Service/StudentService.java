package com.example.webDproject.Service;
import com.example.webDproject.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save only if student does not exist
    public boolean saveStudent(Student student) {
        if (getStudentByRollNumber(student.getRollNumber()) != null) {
            return false; // already exists
        }

        String sql = "INSERT INTO student (name, rollNumber, email, course, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                student.getName(),
                student.getRollNumber(),
                student.getEmail(),
                student.getCourse(),
                student.getPassword()

        );
        return true;
    }

    // Get student by roll number, or null if not found
    public Student getStudentByRollNumber(String rollNumber) {
        String sql = "SELECT * FROM student WHERE roll_number = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{rollNumber}, new StudentRowMapper());
        } catch (Exception e) {
            return null; // student not found
        }
    }

    // Get student by email, or null if not found
    public Student getStudentByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new StudentRowMapper());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null; // student not found
        }
    }
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Debugging: Print raw result
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Course: " + rs.getString("course"));
            System.out.println("ResultSet row:");
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("RollNumber: " + rs.getString("rollNumber"));
            System.out.println("Password: " + rs.getString("password"));

            Student student = new Student();
            student.setName(rs.getString("name"));
            student.setRollNumber(rs.getString("rollNumber"));
            student.setGmail(rs.getString("email")); // Make sure you have a matching "gmail" field in your class
            student.setCourse(rs.getString("course"));
            student.setPassword(rs.getString("password"));
            return student;
        }
    }

}
