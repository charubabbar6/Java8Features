package com.java8.streamapiquestion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
		empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
		empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
		empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));

		empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
		empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
		empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
		empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
		empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));
		
		//System.out.println(empList);
		
		//1. Group the Employees by city.
		Map<String, List<Employee>> empByCity;
		empByCity =empList.stream().collect(Collectors.groupingBy(Employee::getCity));
		System.out.println("Employee grouped by city are:"+empByCity);
		//2. Group the Employees by age.
		/*
		 * Map<Integer,List<Employee>> empByAge;
		 * empByAge=empList.stream().collect(Collectors.groupingBy(Employee::getAge));
		 * System.out.println("Employee grouped by age are:"+empByAge);
		 * // Convert the HashMap to a TreeMap to sort by age
        Map<Integer, List<Employee>> sortedEmpByAge = new TreeMap<>(empByAge);
		 */
		// Collect employees into a TreeMap sorted by age in descending order
        Map<Integer, List<Employee>> empByAge = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getAge,
                () -> new TreeMap<>(Comparator.reverseOrder()), // Custom comparator for descending order
                Collectors.toList() // Downstream collector
            ));

        System.out.println("Employee grouped by age in descending order: " + empByAge);
		/*
		 * Map<Integer, List<Employee>> empByAge = empList.stream()
		 * .collect(Collectors.groupingBy( Employee::getAge, TreeMap::new, // Supplier
		 * for TreeMap Collectors.toList() // Downstream collector ));
		 * System.out.println("Employee grouped by age are:"+empByAge);
		 */
		/*
		 * 3. Find the count of male and female employees present in the organization.
		 * 
		 */
        Map<String, Long> noOfMaleAndFemaleEmployees =empList.stream().
        		collect(Collectors.groupingBy(Employee:: getGender,Collectors.counting()));
        System.out.println("Count of male and female employees present in the organization:: \n" + 
        		noOfMaleAndFemaleEmployees);
        
		/* 4. Print the names of all departments in the organization. */
        System.out.println("Names of all departments in the organization ");
        
        empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
        
		/* 5. Print employee details whose age is greater than 28. */
        System.out.println("employee details whose age is greater than 28");
        
        empList.stream().filter(e->e.getAge()>28).collect(Collectors.toList()).forEach(System.out::println);
        
		/* 6. Find maximum age of employee. */
        
        OptionalInt max=empList.stream().mapToInt(Employee::getAge).max();
        if (max.isPresent()) 
        	System.out.println("Maximum age of Employee: " + max.getAsInt());
		/* 7. Print Average age of Male and Female Employees. */
        
        Map<String, Double> avgAge = empList.stream().collect(Collectors.groupingBy((Employee::getGender),Collectors.averagingInt(Employee::getAge)));
        System.out.println("Average age of Male and Female Employees:: " + avgAge);
        
		/* 8. Print the number of employees in each department. */
		
		  Map<String, Long> countByDept =
		  empList.stream().collect(Collectors.groupingBy
		  (Employee::getDeptName,Collectors.counting()));
		  System.out.println("No of employees in each department");
		  
		  for(Map.Entry<String, Long> entry : countByDept.entrySet()) {
		  System.out.println(entry.getKey() + " : " + entry.getValue()); }
		 
        
     // Group employees by department and count the number of employees in each department
     // Group by department
     // Count the number of employees in each group
        Map<String, Long> empCountByDepartment = empList.stream().collect(Collectors.groupingBy
        		(Employee::getDeptName,Collectors.counting()));

        // Print the number of employees in each department
        empCountByDepartment.forEach((department, count) -> 
            System.out.println("Department: " + department + ", Number of employees: " + count)
        );
		/* 9. Find oldest employee. */
        
        Optional<Employee> oldestEmp =empList.stream().max(Comparator.comparingInt(Employee::getAge));
        Employee oldestEmployee =oldestEmp.get();
        if (oldestEmp.isPresent()) {
           
            System.out.println("Oldest employee details:: \n" + oldestEmployee);
        } else {
            System.out.println("No employees found.");
        }
        
		/* 10. Find youngest female employee. */
        Optional<Employee> youngestEmp =empList.stream().filter(e->e.getGender()=="F").min(Comparator.comparingInt(Employee::getAge));
        Employee youngestEmployee = youngestEmp.get();
        System.out.println("Youngest Female employee details:: \n" + youngestEmployee);
        
		/* 11. Find employees whose age is greater than 30 and less than 30. */
        System.out.println("Employees whose age is greater than 30 and less than30");
        
        Map<Boolean, List<Employee>> partitionEmployeesByAge =
                        empList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30));

        Set<Map.Entry<Boolean, List<Employee>>> empSet = partitionEmployeesByAge.entrySet();

        for (Map.Entry<Boolean, List<Employee>> entry : empSet) {
          if (Boolean.TRUE.equals(entry.getKey())) {
                        System.out.println("Employees greater than 30 years ::" + entry.getValue());
                    } else {
                        System.out.println("Employees less than 30 years ::" + entry.getValue());
                    }
                }
        
        List<Employee> employeesAbove30 = empList.stream()
        	    .filter(e -> e.getAge() > 30)
        	    .collect(Collectors.toList());

        	List<Employee> employeesBelow30 = empList.stream()
        	    .filter(e -> e.getAge() < 30)
        	    .collect(Collectors.toList());

        	System.out.println("Employees above 30:");
        	employeesAbove30.forEach(System.out::println);

        	System.out.println("Employees below 30:");
        	employeesBelow30.forEach(System.out::println);
        	
			/* 12. Find the department name which has the highest number of employees. */
			/*
			 * Map.Entry<String, Long> maxNoOfEmployeesInDept = empList.stream().
			 * collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).
			 * entrySet().stream().max(Map.Entry.comparingByValue()).get();
			 * System.out.println("Max no of employees present in Dept :: " +
			 * maxNoOfEmployeesInDept.getKey());
			 */
        	
        	
        	// Group employees by department and count them
            Map<String, Long> empCountByDept = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
         // Find the department with the maximum number of employees
            Optional<Map.Entry<String, Long>> maxEntry = empCountByDept.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
         // Print the department with the highest number of employees
            maxEntry.ifPresent(entry -> 
                System.out.println("Department with the highest number of employees: " + entry.getKey() +
                                   " with " + entry.getValue() + " employees")
            );
			/* 13. Find if there any employees from HR Department. */
            Optional<Employee> emp = empList.stream().filter(e -> e.getDeptName().equalsIgnoreCase("HR"))
                    .findAny();
            emp.ifPresent(employee->System.out.println("Found employees from HR department " + employee));
            
            // Check if any employee is from the HR department
            boolean hasHrEmployees = empList.stream()
                .anyMatch(e -> "HR".equals(e.getDeptName()));

            if (hasHrEmployees) {
                System.out.println("There are employees from the HR department.");
            } else {
                System.out.println("There are no employees from the HR department.");
            }
            
            
	}

}
