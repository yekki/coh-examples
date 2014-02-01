package com.oracle.handson;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.InvocableMap;
import com.tangosol.util.processor.AbstractProcessor;

public class UpdateWorkAddressProcessor extends AbstractProcessor implements PortableObject{

	private static final long serialVersionUID = -1;

	private Address address;
	
	public UpdateWorkAddressProcessor() {
		
	}
	
	public UpdateWorkAddressProcessor(Address address) {
		
		this.address = address;
	}
	
	@Override
	public Object process(InvocableMap.Entry entry) {
		
		Contact contact = (Contact)entry.getValue();
		
		contact.setWorkAddress(address);
		
		entry.setValue(contact);
		
		System.out.println(contact);
		
		return null;
	}
	
	@Override
	public void readExternal(PofReader reader) throws IOException {
		
		address = (Address)reader.readObject(0);
	}
	
	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeObject(0, address);
	}

}
