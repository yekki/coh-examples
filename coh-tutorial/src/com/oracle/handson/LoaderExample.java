package com.oracle.handson;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.oracle.handson.ContactId;
import com.oracle.handson.Address;
import com.oracle.handson.PhoneNumber;
import com.oracle.handson.Contact;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class LoaderExample {
	
	public static void main(String[] asArg) throws IOException {
		String sFile = asArg.length > 0 ? asArg[0] : DataGenerator.FILENAME;
		String sCache = asArg.length > 1 ? asArg[1] : CACHENAME;

		System.out.println("input file: " + sFile);
		System.out.println("cache name: " + sCache);

		new LoaderExample().load(CacheFactory.getCache(sCache),
				new FileInputStream(sFile));
		CacheFactory.shutdown();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void load(NamedCache cache, InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		Map mapBatch = new HashMap(1024);
		String sRecord;
		int cRecord = 0;

		while ((sRecord = reader.readLine()) != null) {
			// parse record
			String[] asPart = sRecord.split(",");
			int ofPart = 0;
			String sFirstName = asPart[ofPart++];
			String sLastName = asPart[ofPart++];
			ContactId id = new ContactId(sFirstName, sLastName);
			Address addrHome = new Address(
			/* streetline1 */asPart[ofPart++],
			/* streetline2 */asPart[ofPart++],
			/* city */asPart[ofPart++],
			/* state */asPart[ofPart++],
			/* zip */asPart[ofPart++],
			/* country */asPart[ofPart++]);
			Address addrWork = new Address(
			/* streetline1 */asPart[ofPart++],
			/* streetline2 */asPart[ofPart++],
			/* city */asPart[ofPart++],
			/* state */asPart[ofPart++],
			/* zip */asPart[ofPart++],
			/* country */asPart[ofPart++]);
			Map mapTelNum = new HashMap();

			for (int c = asPart.length - 1; ofPart < c;) {
				mapTelNum.put(/* type */asPart[ofPart++], new PhoneNumber(
				/* access code */Short.parseShort(asPart[ofPart++]),
				/* country code */Short.parseShort(asPart[ofPart++]),
				/* area code */Short.parseShort(asPart[ofPart++]),
				/* local num */Integer.parseInt(asPart[ofPart++])));
			}
			Date dtBirth = new Date(Long.parseLong(asPart[ofPart]));

			// Construct Contact and add to batch
			Contact con1 = new Contact(sFirstName, sLastName, addrHome,
					addrWork, mapTelNum, dtBirth);
			System.out.println(con1);
			mapBatch.put(id, con1);

			++cRecord;
			if (cRecord % 1024 == 0) {
				// load batch
				cache.putAll(mapBatch);
				mapBatch.clear();
				System.out.print('.');
				System.out.flush();
			}
		}

		if (!mapBatch.isEmpty()) {
			// load final batch
			cache.putAll(mapBatch);
		}

		System.out.println("Added " + cRecord + " entries to cache");
	}

	public static final String CACHENAME = "ContactsCache";

}