package com.java8.MapvsFlatMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> citywokedin=new ArrayList<>();
		citywokedin.add("Delhi");
		citywokedin.add("Pune");
		citywokedin.add("Chandigarh");
		Employee e=new Employee(1, "Charu", citywokedin);

		List<String> citywokedin1=new ArrayList<>();
		citywokedin1.add("Delhi");
		citywokedin1.add("Pune");
		citywokedin1.add("Banglore");
		Employee e1=new Employee(2, "sushant", citywokedin1);

		List<String> citywokedin2=new ArrayList<>();
		citywokedin2.add("Chandigarh");
		citywokedin2.add("Pune");

		Employee e2=new Employee(3, "arjun", citywokedin2);

		List<Employee> emplist=new ArrayList<>();
		emplist.add(e);
		emplist.add(e1);
		emplist.add(e2);
		System.out.println(emplist);

		/*
		 * List<Integer> empids=new ArrayList<Integer>(); for(Employee eid:emplist) {
		 * empids.add(eid.getId()); } System.out.println(empids);
		 */
		List<Integer> empidlist=emplist.stream().map(emp->emp.getId()).collect(Collectors.toList());
		System.out.println(empidlist);
		List<List<String>> empcitylist=emplist.stream().map(emp->emp.getCitiesworkedin()).collect(Collectors.toList());
		System.out.println(empcitylist);
		Set<List<String>> empcityset=emplist.stream().map(emp->emp.getCitiesworkedin()).collect(Collectors.toSet());
		System.out.println(empcityset);

		List<String> empcityflatlist=emplist.stream().flatMap(emp->emp.getCitiesworkedin().stream()).collect(Collectors.toList());
		System.out.println(empcityflatlist);
		Set<String> empcityflatset=emplist.stream().flatMap(emp->emp.getCitiesworkedin().stream()).collect(Collectors.toSet());
		System.out.println(empcityflatset);
	}

}
