package com.oracle.handson;

import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.processor.AbstractProcessor;
import com.tangosol.util.InvocableMap;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.oracle.handson.Address;
import com.oracle.handson.Contact;
import com.tangosol.net.CacheFactory;

import java.io.IOException;

public class ProcessorExample {
	public ProcessorExample() {
	}

	public static void main(String[] args) {
		NamedCache cache = CacheFactory.getCache("ContactsCache");
		new ProcessorExample().execute(cache);
	}

	public void execute(NamedCache cache) {
		// People who live in Massachusetts moved to an in-state office
		Address addrWork = new Address("200 Newbury St.", "Yoyodyne, Ltd.",
				"Boston", "MA", "02116", "US");

		cache.invokeAll(new EqualsFilter("getHomeAddress.getState", "MA"),
				new OfficeUpdater(addrWork));
	}

	@SuppressWarnings("serial")
	public static class OfficeUpdater extends AbstractProcessor implements
			PortableObject {
	
		public OfficeUpdater() {
		}

		public OfficeUpdater(Address addrWork) {
			m_addrWork = addrWork;
		}

		public Object process(InvocableMap.Entry entry) {
			Contact contact = (Contact) entry.getValue();

			contact.setWorkAddress(m_addrWork);
			entry.setValue(contact);
			System.out.println("Work address was updated for "
					+ contact.getFirstName() + " " + contact.getLastName());
			return null;
		}

		public void readExternal(PofReader reader) throws IOException {
			m_addrWork = (Address) reader.readObject(0);
		}

		public void writeExternal(PofWriter writer) throws IOException {
			writer.writeObject(0, m_addrWork);
		}

		private Address m_addrWork;
	}
}