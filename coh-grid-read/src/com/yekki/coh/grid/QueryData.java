package com.yekki.coh.grid;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import oracle.eclipselink.coherence.integrated.querying.IgnoreDefaultRedirector;

import org.eclipse.persistence.config.QueryHints;

import com.yekki.coh.grid.model.Employee;
import com.yekki.coh.grid.model.PhoneNumber;

public class QueryData {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("coh-grid");
		EntityManager em = emf.createEntityManager();
		
		Query query =  em
				.createNamedQuery("Employee.findByLastNameLike")
				.setParameter("lastName", "Smit%");
		
		//Allows queries to bypass the Coherence cache and be sent directly to the database!!
		//query.setHint(QueryHints.QUERY_REDIRECTOR, new IgnoreDefaultRedirector());
		
		List<Employee> employees =query
			.getResultList();
		for (Employee employee : employees) {
			System.err.println(employee);
			for (PhoneNumber phone : employee.getPhoneNumbers()) {
				System.err.println("\t" + phone);
			}
		}
		
		emf.close();
	}
}
