import java.util.ArrayList;
import java.util.List;
abstract class Employee {
    private String name;
    private String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract double calculateSalary();
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, String id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, String id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class Contractor extends Employee {
    private double contractAmount;

    public Contractor(String name, String id, double contractAmount) {
        super(name, id);
        this.contractAmount = contractAmount;
    }

    @Override
    public double calculateSalary() {
        return contractAmount;
    }
}

class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void generatePayrollReport() {
        System.out.println("Payroll Report:");
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Salary: $" + employee.calculateSalary());
            System.out.println("---------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Payroll payroll = new Payroll();

        Employee emp1 = new FullTimeEmployee("Alice", "FT001", 5000);
        Employee emp2 = new PartTimeEmployee("Bob", "PT001", 20, 80);
        Employee emp3 = new Contractor("Charlie", "CT001", 3000);

        payroll.addEmployee(emp1);
        payroll.addEmployee(emp2);
        payroll.addEmployee(emp3);

        payroll.generatePayrollReport();
    }
}

