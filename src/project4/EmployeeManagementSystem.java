package project4;
import java.util.*;
import java.io.*;

//Main class to manage Employee data
public class EmployeeManagementSystem {
	
	//Contain the list of employees
     List<Employee> employees=new ArrayList<>();
     
     //hashMap with Employee Id as key to faciliate searching of Employees using Id
     HashMap<String,Employee> map=new HashMap<>();
     String DATA_FILE = "employees.dat";
    
     //Method to add an Employee
     public void addEmployee(Employee emp) {
    	 employees.add(emp);
    	 map.put(emp.getId(), emp);
     }
     
     //Method to show all Employee Details
     public void showAll() {
    	 for(Employee emp:employees) {
    		 emp.showDetails();
    		 System.out.println("-".repeat(100));
    	 }
     }
     
     //Search Employee by Id
     public Employee searchbyId(String id) {
    	return map.get(id);
   }
     
     //Search Employee by Name
    public List<Employee> searchByName(String name) {
    	List<Employee> results=new ArrayList<>();
    	for(Employee emp:employees) {
    		if(emp.getName().contains(name)) {
    			results.add(emp);
    		}
    	}
    	return results;
    }
    
    //Search Employee by department
    public List<Employee> searchByDept(String department){
    	List<Employee> results=new ArrayList<>();
    	for(Employee emp:employees) {
        		if(emp.getDepartment().equalsIgnoreCase(department)) {
        			results.add(emp);
        		}
        	}
     return results;
    }
    
    //Method to update Employee Details
    public boolean updateEmployee(String id,String name,String department,String position,double salary, String date) {
    	Employee emp=map.get(id);
    	if(emp==null) {
    		return false;
    	}

    		emp.setName(name);
    		emp.setDepartment(department);
    		emp.setPosition(position);
    		emp.setSalary(salary);
    		emp.setJoinDate(date);
    		return true;
    }
    
    //Method to delete an employee record
    public boolean deleteEmployee(String id) {
    	Employee emp=map.get(id);
    	if(emp==null) {
    		return false;
    	}
    	employees.remove(emp);
    	map.remove(id);
    	return true;
    }
    
    //Method to find number of Employees
    public int countEmployees() {
    	return employees.size();
    }
   
    //Method to find average salary of Employees
    public double findavgsalary() {
    	double total=0;
    	for(Employee emp:employees) {
    		total+=emp.getSalary();
    	}
    	double avg=total/countEmployees();
    	return avg;
    }
    
    //Method to find Employee with highest salary
    public Employee highest() {
    	double max=0;
    	Employee ans=null;
    	for(Employee emp:employees) {
    		if(emp.getSalary()>max) {
    			max=emp.getSalary();
    			ans=emp;
    		}
    	}
    	return ans;
    }
    
  //Method to find Employee with lowest salary
    public Employee lowest() {
    	double min=10000000;
    	Employee ans=null;
    	for(Employee emp:employees) {
    		if(emp.getSalary()<min) {
    			min=emp.getSalary();
    			ans=emp;
    		}
    	}
    	return ans;
    }
    
    //Method to save employee details to a file
    public void saveEmployeesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(employees);
            System.out.println("Employee data saved to file.");
        } catch(IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
    
    //Method to load employee details from a file
    @SuppressWarnings("unchecked")
    public void loadEmployeesFromFile() {
        File file = new File(DATA_FILE);
        if(!file.exists()) {
            System.out.println("No existing employee data found. Starting fresh.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            employees = (ArrayList<Employee>) ois.readObject();
            // Rebuild HashMap
            for(Employee emp : employees) {
                map.put(emp.getId(), emp);
            }
            System.out.println("Loaded " + employees.size() + " employees from file.");
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }
    
    //Main function to provide a menu driven interface to user
    public static void main(String args[]) {
    	EmployeeManagementSystem ems=new EmployeeManagementSystem();
    	Scanner sc=new Scanner(System.in);
    	int choice=0;
    	while(choice!=9) {
    		
    		System.out.println("=== EMPLOYEE MANAGEMENT SYSTEM ===\r\n"
    				+ "1. Add New Employee\r\n"
    				+ "2. View All Employees\r\n"
    				+ "3. Search Employee\r\n"
    				+ "4. Update Employee\r\n"
    				+ "5. Delete Employee\r\n"
    				+ "6. Generate Reports\r\n"
    				+ "7. Save to File\r\n"
    				+ "8. Load from File\r\n"
    				+ "9. Exit\r\n"
    				+ "Enter your choice:");
    		choice=sc.nextInt();
    		
    		//Implements adding an employee
    		if(choice==1) {
    			System.out.println("\n===ADD EMPLOYEE===\n");
    			System.out.println("Enter Employee Id:");
    			sc.nextLine();
    			String id=sc.nextLine();
    			System.out.println("Enter Employee Name:");
    			String name=sc.nextLine();
    			System.out.println("Enter Employee Department:");
    			String dept=sc.nextLine();
    			System.out.println("Enter Employee Position:");
    			String pos=sc.nextLine();
    			System.out.println("Enter Salary");
    			double salary=sc.nextDouble();
    			System.out.println("Enter Date of Joining");
    			sc.nextLine();
    			String date=sc.nextLine();
    			Employee e1=new Employee(id,name,dept,pos,salary,date);
    			ems.addEmployee(e1);
    		}
    		
    		//Implements showing employee details
    		else if(choice==2) {
    			System.out.println("\n===EMPLOYEE DETAILS===\n");
    	    	System.out.println("-".repeat(100));
    	    	ems.showAll();
    		}
    		
    		//Implements Employee search
    		else if(choice==3) {
    			int search=0;
    			System.out.println("=== SEARCH EMPLOYEE ===\r\n"
    					+ "1. Search by ID\r\n"
    					+ "2. Search by Name\r\n"
    					+ "3. Search by Department");
    			search=sc.nextInt();
    			
    			//Implements Employee search by id
    			if(search==1) {
    				System.out.println("Enter Employee Id to search: ");
    				sc.nextLine();
    				String id =sc.nextLine();
    			    Employee emp=ems.searchbyId(id);
    				if(emp==null) {
    					System.out.println("No employee found with given id!!!");
    				}
    				else {
    					System.out.println("Employee found!!!");
    					emp.showDetails();
    				}
    			}
    			
    			//Implements Employee search by name
    			else if(search==2) {
    				System.out.println("Enter Employee Name to search: ");
    				sc.nextLine();
    				String name =sc.nextLine();
    				List<Employee> emps=ems.searchByName(name);
    				if(emps==null) {
    					System.out.println("No employee found with given name!!!");
    				}
    				else {
    					System.out.println("List of employees with matching names: ");
    					for(Employee emp:emps) {
    						emp.showDetails();
    						System.out.println("-".repeat(100));
    				}
    			}
    			}
    			
    			//Implements Employee search by department
    			else {
    				System.out.println("Enter Department to search: ");
    				sc.nextLine();
    				String dept =sc.nextLine();
    				List<Employee> results=ems.searchByDept(dept);
    				if(results==null) {
    					System.out.println("No employee found with Given Department!!!");
    				}
    				else {
    					System.out.println("List of employees working in department: "+dept);
    					for(Employee emp:results) {
    						emp.showDetails();
    						System.out.println("-".repeat(100));
    				}
    			}
    			}
    		}
    		
    		//Implementing employee update
    		else if(choice==4) {
    			System.out.println("\n===UPDATE EMPLOYEE===\n");
    			System.out.println("Enter id of employee to update");
    			sc.nextLine();
    			String id=sc.nextLine();
    			System.out.println("Enter updated details");
    			System.out.println("Enter Employee Name:");
    			String name=sc.nextLine();
    			System.out.println("Enter Employee Department:");
    			String dept=sc.nextLine();
    			System.out.println("Enter Employee Position:");
    			String pos=sc.nextLine();
    			System.out.println("Enter Salary");
    			double salary=sc.nextDouble();
    			System.out.println("Enter Date of Joining");
    			sc.nextLine();
    			String date=sc.nextLine();
    			boolean found=ems.updateEmployee(id, name, dept, pos, salary, date);
    			if(!found) {
    				System.out.println("No user exists with given id");
    			}
    			else {
    				System.out.println("Details of user with id: "+id+ " updated successfully!");
    			}
    			
    		}
    		
    		//Implements deletion of employee
    		else if(choice==5) {
    			System.out.println("\n===DELETE EMPLOYEE===\n");
    			System.out.println("Enter id of employee to delete:");
    			sc.nextLine();
    			String id=sc.nextLine();
    			boolean found=ems.deleteEmployee(id);
    			if(!found) {
    				System.out.println("No user exists with given id");
    			}
    			else {
    				System.out.println("User deleted successfully!");
    			}
    		}
    		
    		//Generate reports
    		else if(choice==6) {
    			System.out.println("\n===Employee Reports===\n");
    			System.out.println("Total Employees: "+ems.countEmployees());
    			System.out.println("Average Salary:  "+ems.findavgsalary());
    			Employee highest=ems.highest();
    			System.out.println("Highest Salary:  "+highest.getSalary()+ "("+highest.getName()+")");
    			Employee lowest=ems.lowest();
    			System.out.println("Lowest Salary:   "+lowest.getSalary()+ "("+lowest.getName()+")");
    		}
    		
    		
    		else if(choice==7) {
    			System.out.println("\n===SAVE EMPLOYEE DETAILS===\n");
    			ems.saveEmployeesToFile();
    		}
    		
    		
    		else if(choice==8) {
    			System.out.println("\n===LOAD EMPLOYEE DETAILS===\n");
    			ems.loadEmployeesFromFile();
    		}
    	}
    }
    
    
}
