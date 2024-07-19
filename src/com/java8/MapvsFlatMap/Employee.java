package com.java8.MapvsFlatMap;

import java.util.List;

public class Employee {


	public Employee(int id, String name, List<String> citiesworkedin) {
		super();
		this.id = id;
		this.name = name;
		this.citiesworkedin = citiesworkedin;
	}
	private int id;
	private String name;
	private List<String> citiesworkedin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCitiesworkedin() {
		return citiesworkedin;
	}
	public void setCitiesworkedin(List<String> citiesworkedin) {
		this.citiesworkedin = citiesworkedin;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", citiesworkedin=" + citiesworkedin + "]";
	}

}
