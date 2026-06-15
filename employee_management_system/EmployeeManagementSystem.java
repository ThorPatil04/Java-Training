import java.sql.*;
import java.util.Scanner;


public class EmployeeManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/company_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Enter a valid integer.");
            }
        }
    }

    public static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Enter a valid number.");
            }
        }
    }

    // Add Employee
    public static void addEmployee() {

        try {

            System.out.print("Employee Name: ");
            String name = sc.nextLine();

            double salary = getDoubleInput("Salary : ");
            int deptId = getIntInput("Department ID : ");

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "INSERT INTO employees(emp_name,salary,dept_id) VALUES(?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, salary);
            pst.setInt(3, deptId);

            int result = pst.executeUpdate();

            if(result > 0)
                System.out.println("Employee Added Successfully");

            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // View Employees
    public static void viewEmployees() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "SELECT e.emp_id,e.emp_name,e.salary,d.dept_name " +
                            "FROM employees e " +
                            "JOIN departments d " +
                            "ON e.dept_id=d.dept_id";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            System.out.println("\n------------------------------------------------");
            System.out.printf("%-5s %-15s %-12s %-15s\n",
                    "ID","NAME","SALARY","DEPARTMENT");
            System.out.println("------------------------------------------------");

            while(rs.next()) {

                System.out.printf("%-5d %-15s %-12.2f %-15s\n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4));
            }

            rs.close();
            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Salary Increment using Transaction
    public static void salaryIncrement() {

        try {

            int empId = getIntInput("Employee ID : ");
            double amount = getDoubleInput("Increment Amount : ");

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            con.setAutoCommit(false);

            String sql =
                    "UPDATE employees SET salary=salary+? WHERE emp_id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setDouble(1, amount);
            pst.setInt(2, empId);

            int result = pst.executeUpdate();

            if(result > 0) {

                con.commit();
                System.out.println("Salary Updated Successfully");

            } else {

                con.rollback();
                System.out.println("Employee Not Found");
            }

            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Batch Insert
    public static void batchInsert() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "INSERT INTO employees(emp_name,salary,dept_id) VALUES(?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            int n = getIntInput("How Many Employees : ");

            for(int i=1;i<=n;i++) {

                System.out.println("\nEmployee " + i);

                System.out.print("Name: ");
                String name = sc.nextLine();

                double salary = getDoubleInput("Salary : ");
                int deptId = getIntInput("Department ID : ");

                pst.setString(1,name);
                pst.setDouble(2,salary);
                pst.setInt(3,deptId);

                pst.addBatch();
            }

            pst.executeBatch();

            System.out.println("Batch Insert Successful");

            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Highest Paid Employee
    public static void highestPaidEmployee() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "SELECT * FROM employees ORDER BY salary DESC LIMIT 1";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                System.out.println("\nHighest Paid Employee");
                System.out.println("ID : " + rs.getInt("emp_id"));
                System.out.println("Name : " + rs.getString("emp_name"));
                System.out.println("Salary : " + rs.getDouble("salary"));
            }

            rs.close();
            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Department Summary
    public static void departmentSummary() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                    "SELECT d.dept_name,COUNT(e.emp_id) TotalEmployees," +
                            "SUM(e.salary) TotalSalary " +
                            "FROM departments d " +
                            "LEFT JOIN employees e " +
                            "ON d.dept_id=e.dept_id " +
                            "GROUP BY d.dept_name";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            System.out.println("\nDepartment Summary");

            while(rs.next()) {

                System.out.println(
                        rs.getString(1)
                                + " | Employees : "
                                + rs.getInt(2)
                                + " | Salary : "
                                + rs.getDouble(3)
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Stored Procedure
    public static void callProcedure() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement cs =
                    con.prepareCall("{call GetEmployeeCount()}");

            ResultSet rs = cs.executeQuery();

            if(rs.next()) {

                System.out.println(
                        "Total Employees : "
                                + rs.getInt("TotalEmployees"));
            }

            rs.close();
            cs.close();
            con.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        int choice;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Loaded Successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver Not Found!");
            return;
        }

        do {

            System.out.println("\n================================");
            System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Salary Increment");
            System.out.println("4. Batch Insert");
            System.out.println("5. Highest Paid Employee");
            System.out.println("6. Department Summary");
            System.out.println("7. Stored Procedure");
            System.out.println("8. Exit");

            choice = getIntInput("Enter Choice : ");

            switch(choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    salaryIncrement();
                    break;

                case 4:
                    batchInsert();
                    break;

                case 5:
                    highestPaidEmployee();
                    break;

                case 6:
                    departmentSummary();
                    break;

                case 7:
                    callProcedure();
                    break;

                case 8:
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 8);

        sc.close();
    }
}