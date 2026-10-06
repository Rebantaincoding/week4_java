package project4;
import java.io.Serializable;

//Employee class
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String department;
    private String position;
    private double salary;
    private String joinDate;
    
    //Parameterized Constructors
	public Employee(String id, String name, String department, String position, double salary, String joinDate) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.joinDate = joinDate;
	}
	
	//Getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	//Show Employee Details
	public void showDetails() {
		System.out.println("Employee id: "+id);
		System.out.println("Employee name: "+name);
		System.out.println("Department: "+department);
		System.out.println("Employee Position: "+position);
		System.out.println("Salary: "+salary);
		System.out.println("Joining Date: "+joinDate);
	}
    
}
