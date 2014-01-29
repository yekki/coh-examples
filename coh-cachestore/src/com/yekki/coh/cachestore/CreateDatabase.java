package com.yekki.coh.cachestore;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateDatabase {
	public static void main(String[] args) {
		
		Map<String,Object> properties = new HashMap<String,Object>();
		properties.put("eclipselink.ddl-generation", "drop-and-create-tables");
		properties.put("eclipselink.ddl-generation.output-mode", "database");
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("employee", properties);
		
		EntityManager em = emf.createEntityManager();
		
		em.close();		
		emf.close();
	}
}
