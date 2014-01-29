package com.yekki.coh.grid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.yekki.coh.grid.model.Address;
import com.yekki.coh.grid.model.Employee;

public class InsertData {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("coh-grid");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Employee employee = createEmployee();
		em.persist(employee);
		em.getTransaction().commit();		
		em.close();
		
		emf.close();
	}

	public static Employee createEmployee() {
		
		Employee employee = new Employee();
		employee.setFirstName("Bob");
		employee.setLastName("Smith");

		Address address = new Address();
		address.setCity("Toronto");
		address.setPostalCode("L5J2B5");
		address.setProvince("ONT");
		address.setStreet("1450 Acme Cr., Suite 4");
		address.setCountry("Canada");
		employee.setAddress(address);
		
		employee.addPhoneNumber("Work", "613", "5558812");

		return employee;
	}
}
