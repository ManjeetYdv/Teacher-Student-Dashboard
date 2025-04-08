package com.example.webDproject.Service;
import com.example.webDproject.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TeacherService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save teacher if Gmail not already used
    public boolean saveTeacher(Teacher teacher) {
        if (getTeacherByGmail(teacher.getGmail()) != null) {
            return false; // already exists
        }

        String sql = "INSERT INTO teacher (name, email, department, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                teacher.getName(),
                teacher.getGmail(),
                teacher.getDepartment(),
                teacher.getPassword()
        );
        return true;
    }

    // Get teacher by Gmail (email), or return null if not found
    public Teacher getTeacherByGmail(String gmail) {
        String sql = "SELECT * FROM teacher WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{gmail}, new TeacherRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    // RowMapper for Teacher
    private static class TeacherRowMapper implements RowMapper<Teacher> {
        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setName(rs.getString("name"));
            teacher.setGmail(rs.getString("email"));
            teacher.setCourses(rs.getString("department"));
            teacher.setPassword(rs.getString("password"));
            return teacher;
        }
    }
}
