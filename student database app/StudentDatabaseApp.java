import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseApp {

    // Database Configuration
    static final String URL = "jdbc:mysql://localhost:3306/college_db";
    static final String USER = "root";
    static final String PASSWORD = "root";


    static Scanner sc = new Scanner(System.in);

    // Add Student
    public static void addStudent() {
        System.out.print("Enter Student id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        

        String sql =
                "INSERT INTO students(id, name, email, course) VALUES(?,?,?,?)";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, course);
            

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Student Added Successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // View Students
    public static void viewStudents() {

        String sql = "SELECT * FROM students";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()
        ) {

            System.out.println("\n-----------------------------------------------");
            System.out.printf("%-5s %-20s %-15s %-20s\n",
                    "ID", "NAME", "EMAIL", "COURSE");
            System.out.println("-----------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-15s %-20s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                );

            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Search Student
    public static void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT * FROM students WHERE id=?";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("\nStudent Found");
                System.out.println("ID     : " + rs.getInt("id"));
                System.out.println("Name   : " + rs.getString("name"));
                System.out.println("Email  : " + rs.getString("email"));
                System.out.println("Course : " + rs.getString("course"));
                

            } else {
                System.out.println("Student Not Found.");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Update Student
    public static void updateStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        

        String sql =
                "UPDATE students SET name=?, course=?, email=? WHERE id=?";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, name);
            pst.setString(2, course);
            pst.setString(3, email);
            pst.setInt(4, id);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Student Updated Successfully.");
            } else {
                System.out.println("Student Not Found.");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Delete Student
    public static void deleteStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM students WHERE id=?";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, id);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Student Deleted Successfully.");
            } else {
                System.out.println("Student Not Found.");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println(" STUDENT DATABASE APPLICATION");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("=================================");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}