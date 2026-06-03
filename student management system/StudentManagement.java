import java.util.ArrayList;
import java.util.List;

abstract class Person {
    private String name;
    private String id;
    private String email;

    public Person(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

class Student extends Person {
    private String course;
    private double grade;

    public Student(String name, String id, String email, String course, double grade) {
        super(name, id, email);
        this.course = course;
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    public void displayStudent() {
        System.out.println("Student ID : " + getId());
        System.out.println("Name       : " + getName());
        System.out.println("Email      : " + getEmail());
        System.out.println("Course     : " + course);
        System.out.println("Grade      : " + grade);
        System.out.println("----------------------------");
    }
}

class Course {
    private String name;
    private int credits;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }
}

public class StudentManagement {
    private List<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        for (Student student : students) {
            student.displayStudent();
        }
    }

    public static void main(String[] args) {

        StudentManagement sm = new StudentManagement();

        Student s1 = new Student(
                "Sai Patil",
                "101",
                "sai@gmail.com",
                "MCA",
                8.9);

        Student s2 = new Student(
                "Rahul Sharma",
                "102",
                "rahul@gmail.com",
                "BCA",
                8.5);

        sm.addStudent(s1);
        sm.addStudent(s2);

        System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");
        sm.displayAllStudents();
    }
}