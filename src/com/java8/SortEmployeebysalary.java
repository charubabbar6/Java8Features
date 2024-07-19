package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

//Given an Employee List ,sort employees bazsed on their salaries in desc order.
public class SortEmployeebysalary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> emplist=new ArrayList<>();
		emplist.add(new Employee(1,30,"Charu",700));
		emplist.add(new Employee(2,28,"arjun",200));
		emplist.add(new Employee(3,15,"suhant",4000));
		emplist.add(new Employee(4,28,"cookie",3500));
		emplist.add(new Employee(5,89,"dollly",2000));
		emplist.add(new Employee(6,18,"bhanu",1200));
		List<Employee> empnewlist=emplist.stream().sorted((o1,o2)->(int)(o2.getEsalary()-o1.getEsalary())).
				collect(Collectors.toList());
		System.out.println(empnewlist);
		System.out.println();
		//fetch only 3 top salried employeed
		List<Employee> empwithtop3sal=emplist.stream().sorted((o1,o2)->(int)(o2.getEsalary()-o1.getEsalary())).
				limit(3).
				collect(Collectors.toList());
		System.out.println(empwithtop3sal);
		System.out.println();

		//Before: [Employee [eid=1, ename=30, esalary=700,ename=Charu], Employee [eid=2, ename=28, esalary=200,ename=Charu], Employee [eid=3, ename=15, esalary=4000,ename=Charu], Employee [eid=4, ename=32, esalary=3500,ename=Charu], Employee [eid=5, ename=89, esalary=2000,ename=Charu], Employee [eid=6, ename=18, esalary=1200,ename=Charu]]
		//After: [Employee [eid=1, ename=30, esalary=1050,ename=Charu], Employee [eid=2, ename=28, esalary=300,ename=Charu], Employee [eid=3, ename=15, esalary=6000,ename=Charu], Employee [eid=4, ename=32, esalary=5250,ename=Charu], Employee [eid=5, ename=89, esalary=3000,ename=Charu], Employee [eid=6, ename=18, esalary=1800,ename=Charu]]

		System.out.println( "Before: " + emplist );
		System.out.println();
		emplist.stream().forEach( emplists -> emplists.setEsalary( Math.round( emplists.getEsalary() * 1.5F ) ) );
		System.out.println( "After: " + emplist );
		System.out.println();
		List<String> empnamelist=emplist.stream().map(x->x.getEname()).collect(Collectors.toList());
		System.out.println("Name of all employees");
		System.out.println();
		System.out.println(empnamelist);
		System.out.println();
		Map<Integer,List<Employee>> empmap=emplist.stream().collect(Collectors.groupingBy(x->x.getEage()));
		System.out.println(empmap);
		System.out.println();
		//Map<Integer,Set<Employee>> empsetmap=emplist.stream().collect(Collectors.groupingBy(x->x.getEage(),Collectors.toSet()));
		Map<Integer,Set<Employee>> empsetmap=emplist.stream().collect(Collectors.groupingBy(x->x.getEage(),TreeMap::new ,Collectors.toSet()));
		System.out.println("empsetmap"+empsetmap);
	}

}
