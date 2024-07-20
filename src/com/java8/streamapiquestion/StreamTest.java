package com.java8.streamapiquestion;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
		empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
		empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
		empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));
		empList.add(new Employee(12, "fgl", 30, 115, "M", "HR", "Chennai", 2014));

		empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
		empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
		empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
		empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
		empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));
		empList.add(new Employee(13, "cha", 22, 150, "F", "IT", "Noida", 2013));

		empList.add(new Employee(10, "arj", 25, 160, "M", "Business", "Blore", 2010));
		empList.add(new Employee(11, "sus", 25, 160, "M", "Business", "Blore", 2010));
		

		// System.out.println(empList);

		// 1. Group the Employees by city.
		Map<String, List<Employee>> empByCity;
		empByCity = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
		System.out.println("Employee grouped by city are:" + empByCity);
		// 2. Group the Employees by age.
		/*
		 * Map<Integer,List<Employee>> empByAge;
		 * empByAge=empList.stream().collect(Collectors.groupingBy(Employee::getAge));
		 * System.out.println("Employee grouped by age are:"+empByAge); // Convert the
		 * HashMap to a TreeMap to sort by age Map<Integer, List<Employee>>
		 * sortedEmpByAge = new TreeMap<>(empByAge);
		 */
		// Collect employees into a TreeMap sorted by age in descending order
		Map<Integer, List<Employee>> empByAge = empList.stream()
				.collect(Collectors.groupingBy(Employee::getAge, () -> new TreeMap<>(Comparator.reverseOrder()), // Custom
																													// comparator
																													// for
																													// descending
																													// order
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
		Map<String, Long> noOfMaleAndFemaleEmployees = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(
				"Count of male and female employees present in the organization:: \n" + noOfMaleAndFemaleEmployees);

		/* 4. Print the names of all departments in the organization. */
		System.out.println("Names of all departments in the organization ");

		empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);

		/* 5. Print employee details whose age is greater than 28. */
		System.out.println("employee details whose age is greater than 28");

		empList.stream().filter(e -> e.getAge() > 28).collect(Collectors.toList()).forEach(System.out::println);

		/* 6. Find maximum age of employee. */

		OptionalInt max = empList.stream().mapToInt(Employee::getAge).max();
		if (max.isPresent())
			System.out.println("Maximum age of Employee: " + max.getAsInt());
		/* 7. Print Average age of Male and Female Employees. */

		Map<String, Double> avgAge = empList.stream()
				.collect(Collectors.groupingBy((Employee::getGender), Collectors.averagingInt(Employee::getAge)));
		System.out.println("Average age of Male and Female Employees:: " + avgAge);

		/* 8. Print the number of employees in each department. */

		Map<String, Long> countByDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println("No of employees in each department");

		for (Map.Entry<String, Long> entry : countByDept.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		// Group employees by department and count the number of employees in each
		// department
		// Group by department
		// Count the number of employees in each group
		Map<String, Long> empCountByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));

		// Print the number of employees in each department
		empCountByDepartment.forEach((department, count) -> System.out
				.println("Department: " + department + ", Number of employees: " + count));
		/* 9. Find oldest employee. */

		Optional<Employee> oldestEmp = empList.stream().max(Comparator.comparingInt(Employee::getAge));
		Employee oldestEmployee = oldestEmp.get();
		if (oldestEmp.isPresent()) {

			System.out.println("Oldest employee details:: \n" + oldestEmployee);
		} else {
			System.out.println("No employees found.");
		}

		/* 10. Find youngest female employee. */
		Optional<Employee> youngestEmp = empList.stream().filter(e -> e.getGender() == "F")
				.min(Comparator.comparingInt(Employee::getAge));
		Employee youngestEmployee = youngestEmp.get();
		System.out.println("Youngest Female employee details:: \n" + youngestEmployee);

		/* 11. Find employees whose age is greater than 30 and less than 30. */
		System.out.println("Employees whose age is greater than 30 and less than30");

		Map<Boolean, List<Employee>> partitionEmployeesByAge = empList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 30));

		Set<Map.Entry<Boolean, List<Employee>>> empSet = partitionEmployeesByAge.entrySet();

		for (Map.Entry<Boolean, List<Employee>> entry : empSet) {
			if (Boolean.TRUE.equals(entry.getKey())) {
				System.out.println("Employees greater than 30 years ::" + entry.getValue());
			} else {
				System.out.println("Employees less than 30 years ::" + entry.getValue());
			}
		}

		List<Employee> employeesAbove30 = empList.stream().filter(e -> e.getAge() > 30).collect(Collectors.toList());

		List<Employee> employeesBelow30 = empList.stream().filter(e -> e.getAge() < 30).collect(Collectors.toList());

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
		Optional<Map.Entry<String, Long>> maxEntry = empCountByDept.entrySet().stream()
				.max(Map.Entry.comparingByValue());
		// Print the department with the highest number of employees
		maxEntry.ifPresent(entry -> System.out.println("Department with the highest number of employees: "
				+ entry.getKey() + " with " + entry.getValue() + " employees"));
		/* 13. Find if there any employees from HR Department. */
		Optional<Employee> emp = empList.stream().filter(e -> e.getDeptName().equalsIgnoreCase("HR")).findAny();
		emp.ifPresent(employee -> System.out.println("Found employees from HR department " + employee));

		// Check if any employee is from the HR department
		boolean hasHrEmployees = empList.stream().anyMatch(e -> "HR".equals(e.getDeptName()));

		if (hasHrEmployees) {
			System.out.println("There are employees from the HR department.");
		} else {
			System.out.println("There are no employees from the HR department.");
		}

		/*
		 * 14. Find the department names that these employees work for, where the number
		 * of employees in the department is over 3.
		 */
		System.out.println("Department names where the number of employees in the department is over 3 :: ");
		empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet()
				.stream().filter(entry -> entry.getValue() > 3).forEach(System.out::println);

		/* 15 . Find distinct department names that employees work for. */
		System.out.println("Distinct department names that employees work for:: ");
		empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);

		/*
		 * 16. Find all employees who lives in ‘Blore’ city, sort them by their name and
		 * print the names of employees.
		 */

		empList.stream().filter(e -> e.getCity().equals("Blore")).sorted(Comparator.comparing(Employee::getName))
				.forEach(e -> System.out.println("Employees staying in Blore:: " + e.getName()));

		List<String> employeeNames = empList.stream().filter(e -> "Blore".equals(e.getCity()))
				.sorted(Comparator.comparing(Employee::getName)).map(Employee::getName).collect(Collectors.toList());

		System.out.println("Employees living in Blore, sorted by name: " + employeeNames);
		/* 17. No of employees in the organisation. */
		System.out.println("No of employees in the organisation :: " + empList.stream().count());

		/* 18. Find employee count in every department */
		System.out.println("number of employees in every department are :: ");
		empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet()
				.stream().forEach(System.out::println);

		Map<String, Long> employeeCountInDepartmentMap = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println("Employee department and its count :- \n" + employeeCountInDepartmentMap);

		/* 19. Find the department which has the highest number of employees. */

		Optional<Map.Entry<String, Long>> deptNameWithHighestEmp = employeeCountInDepartmentMap.entrySet().stream()
				.max(Map.Entry.comparingByValue());
		if (deptNameWithHighestEmp.isPresent()) {
			System.out.println("Department which has the highest number of employees " + deptNameWithHighestEmp.get());
		}

		// Find the department with the highest number of employees
		String departmentWithMaxEmployees = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No Department");

		System.out.println("Department with the highest number of employees: " + departmentWithMaxEmployees);

		/* 20. Sorting a Stream by age and name fields. */
		// Sort employees by age and then by name

		List<Employee> sortedEmployees1 = empList.stream()
				.sorted(Comparator.comparingInt(Employee::getAge).thenComparing(Employee::getName))
				.collect(Collectors.toList());

		System.out.println("Employees sorted by age and name:");
		sortedEmployees1.forEach(System.out::println);

		System.out.println("Sorting based on name and age:: ");
		Comparator<Employee> comparator1 = Comparator.comparing(Employee::getName);
		Comparator<Employee> comparator2 = Comparator.comparingInt(Employee::getAge);
		empList.stream().sorted(comparator1.thenComparing(comparator2)).forEach(System.out::println);

		/* 21. Highest experienced employees in the organization. */
		// way1:
		Optional<Employee> seniorEmp = empList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining))
				.findFirst();
		System.out.println("Senior Employee Details :" + seniorEmp.get());
		// way2:
		// Find the earliest year of joining
		int earliestYearOfJoining = empList.stream().mapToInt(Employee::getYearOfJoining).min()
				.orElseThrow(() -> new RuntimeException("No employees found"));
		// Filter employees who joined in the earliest year
		List<Employee> highestExperiencedEmployees = empList.stream()
				.filter(e -> e.getYearOfJoining() == earliestYearOfJoining).collect(Collectors.toList());
		System.out.println("Highest experienced employees in the organization:");
		highestExperiencedEmployees.forEach(System.out::println);

		// way3
		// Find the earliest year of joining
		OptionalInt earliestYeOfJoining = empList.stream().mapToInt(Employee::getYearOfJoining).min();

		if (earliestYeOfJoining.isPresent()) {
			int earliestYear = earliestYeOfJoining.getAsInt();
			// Filter employees who joined in the earliest year
			List<Employee> highestExpeEmployees = empList.stream().filter(e -> e.getYearOfJoining() == earliestYear)
					.collect(Collectors.toList());

			System.out.println("Highest experienced employees in the organization:");
			highestExpeEmployees.forEach(System.out::println);
		} else {
			System.out.println("No employees found.");
		}

		/* 22. Print average and total salary of the organization. */
		// way1
		// Calculate total and average salary
		LongSummaryStatistics salaryStatistics = empList.stream().mapToLong(Employee::getSalary).summaryStatistics();

		long totalSalary = salaryStatistics.getSum();
		double averageSalary = salaryStatistics.getAverage();

		System.out.println("Total Salary of the organization: " + totalSalary);
		System.out.println("Average Salary of the organization: " + averageSalary);
		// way2
		DoubleSummaryStatistics empSalary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

		System.out.println("Average Salary in the organisation = " + empSalary.getAverage());
		System.out.println("Total Salary in the organisation  = " + empSalary.getSum());

		// 23. Print Average salary of each department.
		// way1
		System.out.println("Print Average salary of each department");
		Map<String, Double> avgSalary = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
		Set<Map.Entry<String, Double>> entrySet = avgSalary.entrySet();
		for (Map.Entry<String, Double> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		// way2
		// Group employees by department and calculate the average salary for each
		// department
		Map<String, Double> avgSalaryByDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));

		// Print the average salary of each department
		avgSalaryByDept.forEach(
				(dept, averageSal) -> System.out.println("Average salary of " + dept + " department: " + averageSal));

		// 24. Find Highest salary in the organisation.
		// way1
		Optional<Employee> empHighest = empList.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst();

		System.out.println("Highest Salary in the organisation : " + empHighest.get().getSalary());
		// way2
		// Find the employee with the highest salary
		Optional<Employee> highestSalaryEmp = empList.stream().max(Comparator.comparingLong(Employee::getSalary));

		if (highestSalaryEmp.isPresent()) {
			System.out.println("Employee with the highest salary: " + highestSalaryEmp.get());
		} else {
			System.out.println("No employees found.");
		}
		// way3
		// Find the highest salary
		OptionalLong highestSalary = empList.stream().mapToLong(Employee::getSalary).max();

		if (highestSalary.isPresent()) {
			long maxSalary = highestSalary.getAsLong();

			// Find all employees who have the highest salary
			List<Employee> highestSalaryEmployees = empList.stream().filter(empl -> empl.getSalary() == maxSalary)
					.collect(Collectors.toList());

			System.out.println("Employees with the highest salary:");
			highestSalaryEmployees.forEach(System.out::println);
		} else {
			System.out.println("No employees found.");
		}
		// 25. Find Second Highest salary in the organisation.

		// way1(if there is no duplicate entry for salaries)

		Optional<Employee> emp2 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.skip(1).findFirst();

		System.out.println("Second Highest Salary in the organisation : : " + emp2.get().getSalary());

		// way2
		// Find the employee with the highest salary
		Optional<Employee> secondHighestSalaryEmp = empList.stream()
				.sorted(Comparator.comparingLong(Employee::getSalary).reversed()).skip(1).findFirst();

		if (highestSalaryEmp.isPresent()) {
			System.out.println("Employee with the second highest salary: " + secondHighestSalaryEmp.get());
		} else {
			System.out.println("No employees found.");
		}

		// way3
		// Find the unique salaries sorted in descending order
		List<Long> sortedSalaries = empList.stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		if (sortedSalaries.size() < 2) {
			System.out.println("Not enough employees to determine the second highest salary.");
			return;
		}

		// Find the second highest salary
		long secondHighestSalary = sortedSalaries.get(1);

		// Find all employees who have the second highest salary
		List<Employee> secondHighestSalaryEmployees = empList.stream()
				.filter(em -> em.getSalary() == secondHighestSalary).collect(Collectors.toList());

		System.out.println("Employees with the second highest salary:");
		secondHighestSalaryEmployees.forEach(emplo -> System.out.println(emplo.getName()));
		// way4:
		// Find the distinct salaries sorted in descending order
		List<Long> distinctSalaries = empList.stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		// Check if there are at least two distinct salaries
		if (distinctSalaries.size() < 2) {
			System.out.println("Not enough distinct salaries to determine the second highest salary.");
			return;
		}

		// Get the second highest salary
		long secondHighestSal = distinctSalaries.get(1);

		// Find all employees with the second highest salary
		List<Employee> secondHighestSalEmployees = empList.stream()
				.filter(empll -> empll.getSalary() == secondHighestSal).collect(Collectors.toList());

		System.out.println("Second Highest Salary in the organisation: " + secondHighestSalary);
		System.out.println("Employees with the second highest salary:");
		secondHighestSalEmployees.forEach(emply -> System.out.println(emply.getName()));

		// 26. Nth Highest salary.
		int n = 7;// this can be any nth number highest salary
		Optional<Employee> emp3 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.skip(n - 1).findFirst();
		System.out.println(n + "th Highest Salary in the organisation : " + emp3.get().getSalary());

		// way2:
		// Find the distinct salaries sorted in descending order
		List<Long> distinctSalarie = empList.stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		if (distinctSalarie.size() < n) {
			System.out.println("Not enough distinct salaries to determine the " + n + "th highest salary.");
			return;
		}

		// Get the Nth highest salary
		long nthHighestSalary = distinctSalarie.get(n - 1);

		// Find all employees with the Nth highest salary
		List<Employee> nthHighestSalaryEmployees = empList.stream().filter(empy -> empy.getSalary() == nthHighestSalary)
				.collect(Collectors.toList());

		System.out.println(n + "th Highest Salary in the organisation: " + nthHighestSalary);
		System.out.println("Employees with the " + n + "th highest salary:");
		nthHighestSalaryEmployees.forEach(emply -> System.out.println(emply.getName()));

		// 27. Find highest paid salary in the organisation based on gender.

		Map<String, Optional<Employee>> highestPaidMFEmployee = empList.stream().collect(Collectors.groupingBy(
				Employee::getGender, Collectors.maxBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));
		System.out.println("Highest paid male and female employee : " + highestPaidMFEmployee);

		// way2:
		// Find the highest salary for each gender
		Map<String, Long> highestSalaryByGender = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.mapping(Employee::getSalary, Collectors.maxBy(Comparator.naturalOrder()))))
				.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElse(0L)));

		// Print the results
		highestSalaryByGender.forEach((gender, highestSal) -> {
			System.out.println("Highest salary for gender " + gender + " is: " + highestSal);

			List<Employee> employeesWithHighestSalary = empList.stream()
					.filter(empl -> empl.getGender().equals(gender) && empl.getSalary() == highestSal)
					.collect(Collectors.toList());

			System.out.println("Employees with the highest salary for gender " + gender + ":");
			employeesWithHighestSalary.forEach(empl -> System.out.println(empl.getName()));
		});
		// 28. Find lowest paid salary in the organisation based in the organisation.
		Map<String, Optional<Employee>> lowestPaidMFEmployee = empList.stream().collect(Collectors.groupingBy(
				Employee::getGender, Collectors.minBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));

		System.out.println("Lowest paid male and female employee : " + lowestPaidMFEmployee);

		//way2:
		// Find the lowest salary in the organization
        OptionalLong lowestSalaryOpt = empList.stream()
            .mapToLong(Employee::getSalary)
            .min();

        if (lowestSalaryOpt.isPresent()) {
            long lowestSalary = lowestSalaryOpt.getAsLong();
            
            // Find all employees with the lowest salary
            List<Employee> employeesWithLowestSalary = empList.stream()
                .filter(empl -> empl.getSalary() == lowestSalary)
                .collect(Collectors.toList());

            System.out.println("Lowest salary in the organization: " + lowestSalary);
            System.out.println("Employees with the lowest salary:");
            employeesWithLowestSalary.forEach(empl -> System.out.println(empl.getName()));
        } else {
            System.out.println("No employees found.");
        }
        //29.Find lowest paid salary in the organisation based on gender.
     // Find the lowest salary by gender
        Map<String, Long> lowestSalariesByGender = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.mapping(Employee::getSalary, Collectors.minBy(Comparator.naturalOrder()))
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().orElse(0L)
            ));

        // Find and print employees with the lowest salary for each gender
        lowestSalariesByGender.forEach((gender, lowestSalary) -> {
            System.out.println("Lowest salary for gender " + gender + " is: " + lowestSalary);

            List<Employee> employeesWithLowestSalary = empList.stream()
                .filter(empl -> empl.getGender().equals(gender) && empl.getSalary() == lowestSalary)
                .collect(Collectors.toList());

            System.out.println("Employees with the lowest salary for gender " + gender + ":");
            employeesWithLowestSalary.forEach(empl -> System.out.println(empl.getName()));
        });
        //30. Sort the employees salary in the organisation in ascending order
        //way1
        System.out.println("Sorting the organisation's employee salary in ascending order ");
        empList.stream().sorted(Comparator.comparingLong(Employee::getSalary)).collect(Collectors.toList()) .forEach(System.out::println);
    //way2
        // Print sorted salaries
        List<Long> sortedSalariesemp = empList.stream()
            .map(Employee::getSalary)
            .distinct() // Ensure distinct salaries
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Salaries in ascending order:");
        sortedSalariesemp.forEach(System.out::println);
        
        //way3
     // Sort employees by salary in ascending order
        List<Employee> sortedEmployees = empList.stream()
            .sorted(Comparator.comparingLong(Employee::getSalary))
            .collect(Collectors.toList());

        System.out.println("Employees sorted by salary in ascending order:");
        sortedEmployees.forEach(empl -> System.out.println(empl.getName() + ": " + empl.getSalary()));
	//31. Sort the employees salary in the organisation in descending order.
        //way1
        System.out.println("Sorting the organisation's employee salary in decending order ");
        empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).collect(Collectors.toList()) .forEach(System.out::println);
    //way2
        // Print sorted salaries
        List<Long> sortedDistinctSalaries = empList.stream()
                .map(Employee::getSalary) // Extract salaries
                .distinct() // Ensure distinct salaries
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .collect(Collectors.toList()); // Collect into a list


        System.out.println("Salaries in decending order:");
        sortedDistinctSalaries.forEach(System.out::println);
        
        //way3
     // Sort employees by salary in decending order
        List<Employee> sortedEmployee = empList.stream()
            .sorted(Comparator.comparingLong(Employee::getSalary).reversed())
            .collect(Collectors.toList());

        System.out.println("Employees sorted by salary in decending order:");
        sortedEmployee.forEach(empl -> System.out.println(empl.getName() + ": " + empl.getSalary()));
        
        //32. Highest salary based on department.
        //way1
        System.out.println("Highest salary dept wise:: \n" + empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.collectingAndThen(Collectors.toList(),
        		list -> list.stream().max(Comparator.comparingDouble(Employee::getSalary))))));
        
        //way2
     // Find highest salary for each department
        Map<String, Long> highestSalariesByDept = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getDeptName, // Group by department
                Collectors.mapping(
                    Employee::getSalary, // Extract salary
                    Collectors.maxBy(Comparator.naturalOrder()) // Find the maximum salary
                )
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey, // Department name
                e -> e.getValue().orElse(0L) // Maximum salary, default to 0 if not present
            ));

        // Print the highest salary for each department
        highestSalariesByDept.forEach((dept, salary) -> 
            System.out.println("Department: " + dept + ", Highest Salary: " + salary)
        );
        //way3:
     // Step 1: Find the highest salary in each department
        Map<String, Long> highestSalByDept = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getDeptName,
                Collectors.mapping(Employee::getSalary, Collectors.maxBy(Comparator.naturalOrder()))
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().orElse(0L)
            ));

        // Step 2: Find employees with the highest salary in each department
        Map<String, List<Employee>> employeesWithHighestSalaryByDept = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getDeptName
            ));

        // Print highest salary and list employees with that salary for each department
        highestSalByDept.forEach((dept, highsal) -> {
            List<Employee> employees = employeesWithHighestSalaryByDept.get(dept).stream()
                .filter(empl -> empl.getSalary() == highsal)
                .collect(Collectors.toList());

            System.out.println("Department: " + dept + ", Highest Salary: " + highsal);
            System.out.println("Employees with the highest salary:");
            employees.forEach(System.out::println);
        });
        
        //33. Print list of employee’s second highest record based on department
        System.out.println("Highest second salary dept wise:: \n" + empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(),
                list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst()))));
     
        //way2:handling the null pointer case
     // Group employees by department and collect their salaries
        Map<String, List<Long>> salariesByDept = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getDeptName,
                Collectors.mapping(Employee::getSalary, Collectors.toList())
            ));

        // Find the second highest salary in each department
        Map<String, Long> secondHighestSalariesByDept = salariesByDept.entrySet().stream()
            .map(entry -> {
                List<Long> sortedSalary = entry.getValue().stream()
                    .distinct() // Remove duplicates
                    .sorted(Comparator.reverseOrder()) // Sort in descending order
                    .collect(Collectors.toList());

                if (sortedSalary.size() < 2) {
                    return null; // Not enough distinct salaries
                }

                return new AbstractMap.SimpleEntry<>(entry.getKey(), sortedSalary.get(1)); // Get the second highest salary
            })
            .filter(Objects::nonNull) // Filter out entries with null values
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print the list of employees with the second highest salary for each department
        secondHighestSalariesByDept.forEach((dept, secondHighSalary) -> {
            if (secondHighSalary != null) {
                List<Employee> employeesWithSecondHighest = empList.stream()
                    .filter(empl -> empl.getDeptName().equals(dept) && empl.getSalary() == secondHighSalary)
                    .collect(Collectors.toList());

                System.out.println("Department: " + dept + ", Second Highest Salary: " + secondHighSalary);
                System.out.println("Employees with the second highest salary:");
                employeesWithSecondHighest.forEach(System.out::println);
            } else {
                System.out.println("Department: " + dept + " does not have a second highest salary.");
            }
        });
        
        //34. Sort the employees salary in each department in ascending order
        //way1
        System.out.println("Sorting the department wise employee salary in ascending order:: ");
        Map<String, Stream<Employee>> sortedEmployeeAsc = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, 
                                                           Collectors.collectingAndThen(Collectors.toList(),
                                                           list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary)))));

        sortedEmployeeAsc.forEach((k,v)->{
                    System.out.println(k);
                    System.out.println(v.collect(Collectors.toList()));
                });
        
        //way2
     // Group employees by department and sort their salaries in ascending order
        Map<String, List<Employee>> sortedEmployeesByDept = empList.stream()
            .collect(Collectors.groupingBy(
                Employee::getDeptName,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .sorted(Comparator.comparingLong(Employee::getSalary))
                        .collect(Collectors.toList())
                )
            ));

        // Print the sorted employees by department
        sortedEmployeesByDept.forEach((dept, employees) -> {
            System.out.println("Department: " + dept);
            employees.forEach(System.out::println);
        });
        
       // 35. Sort the employees salary in each department in descending order
        System.out.println("Sorting the department wise employee salary in descending order ");
        Map<String, Stream<Employee>> sortedEmployeeDesc = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, 
                                                            Collectors.collectingAndThen(Collectors.toList(),
                                                            list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()))));

        	sortedEmployeeDesc.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v.collect(Collectors.toList()));
        });
	}

}
