package com.oracle.handson;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.QueryHelper;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.EqualsFilter;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void updateWorkProcess(NamedCache cache) {
	
		Address address = new Address();
		address.setCity("Shanghai");
		address.setCountry("China");
		address.setStreet1("Pudong");
		address.setStreet2("Jinqiao");
		address.setZip("200001");
		
		EqualsFilter filter = new EqualsFilter("getFirstName", "John");
		UpdateWorkAddressProcessor processor = new UpdateWorkAddressProcessor(address);
		
		Map map = cache.invokeAll(filter, processor);
		
		Set keys = map.keySet();
		
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
				
			System.out.println(iter.next());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void queryByFilter(NamedCache cache) {
		
		Filter filter = new AndFilter(new EqualsFilter("getFirstName", "John"), new EqualsFilter("getLastName", "Xxqm"));
		UpdateWorkAddressProcessor processor = new UpdateWorkAddressProcessor(new Address());
		Map map = cache.invokeAll(filter, processor);
		
		printContacts(map);
	}
	
	@SuppressWarnings("rawtypes")
	private static void printContacts(Map contacts) {
		
		Set keys = contacts.keySet();
		
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
				
			System.out.println(iter.next());
		}
	}
	
	private static void printContacts(Set<Map.Entry<String, Contact>> contacts) {
		
		for (Map.Entry<String, Contact> o :contacts) {
			
			System.out.format("Key=%s\nValue=%s", o.getKey(), o.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void queryByCQL(NamedCache cache) {
		
		Filter filter = QueryHelper.createFilter("firstName='John' and lastName='Xxqm'");
		
		printContacts(cache.entrySet(filter));
	}
	
	public static void trans(NamedCache cache) {
		
		Contact contact = (Contact)cache.get("John Xxqm");
		System.out.println(contact);
	}
	
	public static void main(String[] args) {
		
		NamedCache cache = CacheFactory.getCache("ContactsCache");
		
		//updateWorkProcess(cache);
		//queryByFilter(cache);
		long start = System.currentTimeMillis();
		//queryByCQL(cache);
		trans(cache);
		System.out.println("elapsed:" + (System.currentTimeMillis() - start));
		CacheFactory.shutdown();
	}
}
