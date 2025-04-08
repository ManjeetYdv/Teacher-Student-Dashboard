package com.example.webDproject;

import com.example.webDproject.Entities.CGPA;
import com.example.webDproject.Entities.Student;
import com.example.webDproject.Entities.Teacher;
import com.example.webDproject.Service.CGPAService;
import com.example.webDproject.Service.StudentService;
import com.example.webDproject.Service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private Encryption encryption;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CGPAService cgpaService;

    @RequestMapping("/")
    public String homePage() {
      //  System.out.println("Home Page");
        return "login";
    }

    @RequestMapping("/login")
    public String loginPage() {
       // System.out.println("Login Page");
        return "login";
    }
    @RequestMapping("/signup")
    public String signupPage() {
       // System.out.println("Singup Page");
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(HttpServletRequest request) {
        String role = request.getParameter("role");

        if ("STUDENT".equalsIgnoreCase(role)) {
            // Extract student-specific fields
            String name = request.getParameter("name");
            String rollNumber = request.getParameter("rollNumber");
            String course = request.getParameter("course");
            String gmail = request.getParameter("gmail");
            String password = request.getParameter("password");

            // Encrypt the password
            String hashedPassword = Encryption.hashPassword(password);

            // Create and save Student object
            Student student = new Student();
            student.setName(name);
            student.setRollNumber(rollNumber);
            student.setCourse(course);
            student.setGmail(gmail);
            student.setPassword(hashedPassword);

            System.out.println(student.toString());
            System.out.println("Student signed up: " + student);

             studentService.saveStudent(student); // Save via service/repository

        } else if ("TEACHER".equalsIgnoreCase(role)) {
            // Extract teacher-specific fields
            String name = request.getParameter("TeacherName");
            String gmail = request.getParameter("TeacherGmail");
            String department = request.getParameter("department");
            String password = request.getParameter("TeacherPassword");

            // Encrypt the password
            String hashedPassword = Encryption.hashPassword(password);

            // Create and save Teacher object
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setGmail(gmail);
            teacher.setCourses(department); // courses is used as department here
            teacher.setPassword(password);

          //  System.out.println(teacher.toString());
         //   System.out.println("Teacher signed up: " + teacher);

            teacherService.saveTeacher(teacher); // Save via service/repository
        }

        return "login"; // Redirect to login page
    }

    //handle login

    @PostMapping("/login")
    public String handleLogin(HttpServletRequest request, HttpSession session, Model model) {
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("STUDENT".equalsIgnoreCase(role)) {
            Student student = studentService.getStudentByEmail(email);
            //System.out.println(student.toString());
            if (student != null && Encryption.verifyPassword(password, student.getPassword())) {
               // System.out.println("letsgooo");
                session.setAttribute("student", student);
                return "redirect:/student/dashboard?rollNumber=" + student.getRollNumber();
            } else {
                //System.out.println("wrong credentials:+ "+password+" " +email+" "+student.getPassword()+" "+student.getEmail());

                model.addAttribute("error", "Invalid student credentials");
                return "login";
            }

        }
        else if ("TEACHER".equalsIgnoreCase(role)) {
            Teacher teacher = teacherService.getTeacherByGmail(email);
            if (teacher != null && password.equals(teacher.getPassword())) {
               // System.out.println("Teacher login success");
                session.setAttribute("teacher", teacher);
                return "redirect:/teacher/dashboard?email=" + teacher.getGmail();
            } else {
              //  System.out.println(password);
               // System.out.println(teacher.getPassword());
               // System.out.println(Encryption.verifyPassword(password, teacher.getPassword()));
               // System.out.println("Teacher login failed "+ teacher.toString());
                model.addAttribute("error", "Invalid teacher credentials");
                return "login";
            }
        }
        model.addAttribute("error", "Please select a valid role");
        return "login";
    }

    @GetMapping("/student/dashboard")
    public String showStudentDashboard(HttpSession session, Model model) {
        // Get student object from session
        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/login?error=unauthorized";
        }

        // Use roll number to get CGPA list
        List<CGPA> cgpaList = cgpaService.getCgpaByRollNumber(student.getRollNumber());

        // Add to model
        model.addAttribute("student", student);
        model.addAttribute("cgpaList", cgpaList);

        return "student_dashboard";
    }
    @GetMapping("/teacher/dashboard")
    public String showTeacherDashboard(Model model) {
        List<Student> students = studentService.getAllStudents(); // basic details
        Map<String, List<CGPA>> cgpaMap = new HashMap<>(); // rollNumber -> List<CGPA>

        for (Student student : students) {
            List<CGPA> cgpaList = cgpaService.getCgpaByRollNumber(student.getRollNumber());
            cgpaMap.put(student.getRollNumber(), cgpaList);
        }

        model.addAttribute("students", students);
        model.addAttribute("cgpaMap", cgpaMap);
        return "teacher-dashboard";
    }
    @PostMapping("/teacher/update-cgpa")
    public String updateCgpa(@RequestParam String rollNumber,
                             @RequestParam int semester,
                             @RequestParam double cgpa) {
        cgpaService.updateOrInsertCgpa(rollNumber, semester, cgpa);
        return "redirect:/teacher/dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
