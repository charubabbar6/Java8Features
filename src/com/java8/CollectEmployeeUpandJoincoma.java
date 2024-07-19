package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectEmployeeUpandJoincoma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> emplist=new ArrayList<>();
		emplist.add(new Employee(1,30,"Charu",700));
		emplist.add(new Employee(2,28,"arjun",200));
		emplist.add(new Employee(3,15,"suhant",4000));
		emplist.add(new Employee(4,28,"cookie",3500));
		emplist.add(new Employee(5,89,"dollly",2000));
		emplist.add(new Employee(6,18,"bhanu",1200));
		List<String> employeeNames = emplist.stream().map(Employee::getEname).collect(Collectors.toList());

		//List<Employee> empnewlist=emplist.stream().sorted((o1,o2)->(int)(o2.getEsalary()-o1.getEsalary())).
				//collect(Collectors.toList());
		System.out.println(employeeNames);
		String names = employeeNames.stream().map(name -> name.toUpperCase()).collect(Collectors.joining(","));
		System.out.println(names);
	}

}
