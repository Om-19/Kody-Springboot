package com.streams.pract;

import java.util.Objects;

public class Department {

	private int deptId;
	private String deptName;
	private String location;

	// Constructors
	public Department() {
		
	}

	public Department(String deptName, String location) {
		this.deptName = deptName;
		this.location = location;
	}

	public Department(int deptId, String deptName, String location) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.location = location;
	}

	// Getters & Setters
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", location=" + location + "]";
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Department)) return false;
	    Department d = (Department) o;
	    return deptName.equals(d.deptName);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(deptName);
	}
}

