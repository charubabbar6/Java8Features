package com.java8;

import java.util.Objects;

public class Employee {
	private int eid;
	private int eage;
	private String ename;
	private long esalary;



	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, int eage,String ename, long esalary) {
		super();
		this.eid = eid;
		this.eage=eage;
		this.ename = ename;
		this.esalary = esalary;
	}
	public int getEage() {
		return eage;
	}

	public void setEage(int eage) {
		this.eage = eage;
	}

	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public long getEsalary() {
		return esalary;
	}
	public void setEsalary(long esalary) {
		this.esalary = esalary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + eage + ", esalary=" + esalary + ",ename="+ ename+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(ename, other.ename);
	}

	/*
	 * @Override public boolean equals(Object obj) { if(this==obj) return true;
	 * if((obj==null) || (getClass()!=obj.getClass())) return false; Employee
	 * other=(Employee) obj; if(ename==null) { if(other.ename!=null) return false;
	 * 
	 * }else if(!ename.equals(other.ename)) return false;
	 * 
	 * return true;
	 * 
	 * }
	 */
	
	

}
