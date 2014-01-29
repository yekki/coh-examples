package com.yekki.coh.cachestore;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.yekki.coh.cachestore.model.Employee;

public class WriteDataClient {
	private static final int NEW_EMP_ID = 1;
	private static final int NON_EXISTANT_EMP_ID = 2;

	public static void main(String[] args) {
		

		NamedCache employeeCache = CacheFactory.getCache("Employee");
		Employee employee = new Employee();
		employee.setId(NEW_EMP_ID);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employeeCache.put(NEW_EMP_ID, employee);
		
		System.out.println("New Employee from cache is: "
				+ employeeCache.get(NEW_EMP_ID));
		
		System.out.println("Non-existant Employee from cache is: "
				+ employeeCache.get(NON_EXISTANT_EMP_ID));
	}
}
