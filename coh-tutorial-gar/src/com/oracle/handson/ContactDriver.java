package com.oracle.handson;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;

public class ContactDriver {

	public ContactDriver() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		NamedCache contact = CacheFactory.getCache("contact");

		Address homeAddress = new Address("4157 Wash Ave", "Suite 4",
				"Burlingame", "CA", "94407", "USA");
		Address workAddress = new Address("500 Oracle Pkwy", "MS989",
				"Redwood Shores", "CA", "94065", "USA");
		Date date = new java.sql.Date(new GregorianCalendar(2011, 05, 01)
				.getTime().getTime());
		PhoneNumber phonenumber = new PhoneNumber((short) 11, (short) 650,
				(short) 506, 7000);
		Map map = new HashMap();
		map.put("home", phonenumber);

		Contact con1 = new Contact("Tom", "Dunn", homeAddress, workAddress,
				map, date);

		contact.put(con1.getFirstName(), con1);

		Contact con2 = (Contact) contact.get(con1.getFirstName());

		if (con2.getFirstName().equals(con1.getFirstName())) {
			
			System.out.println("They are the same!!");
		}
	}
}
