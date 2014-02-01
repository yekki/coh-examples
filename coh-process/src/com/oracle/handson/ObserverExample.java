package com.oracle.handson;

import com.tangosol.net.NamedCache;

import com.tangosol.util.AbstractMapListener;
import com.tangosol.util.MapEvent;

import com.oracle.handson.Contact;

import com.tangosol.net.CacheFactory;

import java.io.IOException;

public class ObserverExample {

	public ObserverExample() {
	}

	public static void main(String[] args) {
		NamedCache cache = CacheFactory.getCache("ContactsCache");
		new ObserverExample().observe(cache);
		try {
			System.in.read();
		} catch (IOException e) {
		}
	}

	public void observe(NamedCache cache) {
		cache.addMapListener(new ContactChangeListener());
	}

	public class ContactChangeListener extends AbstractMapListener {
		
		public void entryInserted(MapEvent event) {
			System.out.println(event);
		}

		public void entryUpdated(MapEvent event) {
			Contact contactOld = (Contact) event.getOldValue();
			Contact contactNew = (Contact) event.getNewValue();
			StringBuffer sb = new StringBuffer();

			if (!contactOld.getHomeAddress()
					.equals(contactNew.getHomeAddress())) {
				sb.append("Home address ");
			}
			if (!contactOld.getWorkAddress()
					.equals(contactNew.getWorkAddress())) {
				sb.append("Work address ");
			}
			if (!contactOld.getTelephoneNumbers().equals(
					contactNew.getTelephoneNumbers())) {
				sb.append("Telephone ");
			}
			if (contactOld.getAge() != contactNew.getAge()) {
				sb.append("Birthdate ");
			}
			sb.append("was updated for ").append(event.getKey());
			System.out.println(sb);
		}

		public void entryDeleted(MapEvent event) {
			System.out.println(event.getKey());
		}
	}
}