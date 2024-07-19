package com.java8.GroupingBy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java8.Employee;

public class EmployeeGroupingByAge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<Employee> stream = Stream.of(new Employee(1,30,"Charu",700),
											new Employee(2,28,"arjun",200),
											new Employee(3,15,"suhant",4000),
											new Employee(4,28,"cookie",3500),
											new Employee(7,28,"cookie",300),
											new Employee(5,89,"dollly",2000),
											new Employee(6,18,"bhanu",1200));
		
		  //Map<Integer, List<Employee>> employeemap= stream.collect(Collectors.groupingBy(emp->emp.getEage()));
		 // System.out.println(employeemap);
		
			/*
			 * Map<Integer, Set<Employee>>
			 * employeesetmap=stream.collect(Collectors.groupingBy(emp->emp.getEage(),
			 * Collectors.toSet()));//override equals and hashcode method based on name if
			 * we use collectors.toset System.out.println(employeesetmap);
			 */
		//if want result in sorting order
		  Map<Integer, Set<Employee>> employeesetmap=stream.collect(Collectors.groupingBy(emp->emp.getEage(),TreeMap:: new ,Collectors.toSet()));//override equals and hashcode method based on name if we use collectors.toset
		  System.out.println(employeesetmap);
	}

}
