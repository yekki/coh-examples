package com.oracle.handson;

import com.oracle.handson.model.Employee;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class RunEmployeeExample {
	public RunEmployeeExample() {
	}

	public static void main(String[] args) {
		long empId = 10001;

		NamedCache employees = CacheFactory.getCache("Employee");

		Employee emp = (Employee) employees.get(empId);

		System.out.println("Employee " + emp.getFirstName() + " "
				+ emp.getLastName() + ", Emp No = $" + emp.getEmpNo());

	}
}