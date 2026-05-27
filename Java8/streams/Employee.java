package com.streams.pract;

import java.time.LocalDate;

public class Employee {

	private int id; // BIGINT
	private String name; // VARCHAR
	private double salary; // NUMERIC
	private LocalDate joiningDate; // DATE
	private boolean isActive; // BOOLEAN
	private EmployeeType employeeType; // ENUM
	private Department department; // OBJECT

	// Constructors
	public Employee() {
	}

	public Employee(String name, double salary, LocalDate joiningDate, boolean isActive, EmployeeType employeeType,
			Department department) {
		this.name = name;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.isActive = isActive;
		this.employeeType = employeeType;
		this.department = department;
	}

	public Employee(int id, String name, double salary, LocalDate joiningDate, boolean isActive,
			EmployeeType employeeType, Department department) {

		this.id = id;
		this.name = name;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.isActive = isActive;
		this.employeeType = employeeType;
		this.department = department;
	}

	// Getters & Setters
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", joiningDate=" + joiningDate
				+ ", isActive=" + isActive + ", employeeType=" + employeeType + ", department=" + department + "]";
	}
	
	
}
