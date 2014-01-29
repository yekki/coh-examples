package com.oracle.handson;

import com.oracle.handson.Address;
import com.oracle.handson.Contact;
import com.oracle.handson.PhoneNumber;
import com.tangosol.util.Base;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.sql.Date;

import java.util.Collections;
import java.util.Random;

public class DataGenerator {

	public static void main(String[] asArg) throws IOException {
		
		String sFile = asArg.length > 0 ? asArg[0] : FILENAME;
		int cCon = asArg.length > 1 ? Integer.parseInt(asArg[1]) : 1000;
		OutputStream out = new FileOutputStream(sFile);

		generate(out, cCon);
		
		out.close();
	}

	public static void generate(OutputStream out, int cContacts)
			throws IOException {
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(out)));

		for (int i = 0; i < cContacts; ++i) {
			
			StringBuffer sb = new StringBuffer(256);

			// contact person
			sb.append("John,").append(getRandomName()).append(',');

			// home and work addresses
			sb.append(Integer.toString(Base.getRandom().nextInt(999)))
					.append(" Beacon St.,,") /* street1,empty street2 */
					.append(getRandomName()) /* random city name */
					.append(',').append(getRandomState()).append(',')
					.append(getRandomZip())
					.append(",US,Yoyodyne Propulsion Systems,")
					.append("330 Lectroid Rd.,Grover's Mill,")
					.append(getRandomState()).append(',')
					.append(getRandomZip()).append(",US,");

			// home and work telephone numbers
			sb.append("home,")
					.append(Base.toDelimitedString(getRandomPhoneDigits(), ","))
					.append(",work,")
					.append(Base.toDelimitedString(getRandomPhoneDigits(), ","))
					.append(',');

			// random birth date in millis before or after the epoch
			sb.append(getRandomDateInMillis());

			writer.println(sb);
		}
		writer.flush();
	}

	private static String getRandomName() {
		Random rand = Base.getRandom();
		int cCh = 4 + rand.nextInt(7);
		char[] ach = new char[cCh];

		ach[0] = (char) ('A' + rand.nextInt(26));
		for (int of = 1; of < cCh; ++of) {
			ach[of] = (char) ('a' + rand.nextInt(26));
		}
		return new String(ach);
	}

	private static int[] getRandomPhoneDigits() {
		Random rand = Base.getRandom();
		return new int[] { 11, // access code
				rand.nextInt(99), // country code
				rand.nextInt(999), // area code
				rand.nextInt(9999999) // local number
		};
	}

	private static PhoneNumber getRandomPhone() {
		int[] anPhone = getRandomPhoneDigits();

		return new PhoneNumber((short) anPhone[0], (short) anPhone[1],
				(short) anPhone[2], anPhone[3]);

	}

	private static String getRandomZip() {
		return Base.toDecString(Base.getRandom().nextInt(99999), 5);
	}

	private static String getRandomState() {
		return STATE_CODES[Base.getRandom().nextInt(STATE_CODES.length)];
	}

	private static long getRandomDateInMillis() {
		return (Base.getRandom().nextInt(40) - 20) * Contact.MILLIS_IN_YEAR;
	}

	public static Contact getRandomContact() {
		return new Contact("John", getRandomName(), new Address(
				"1500 Boylston St.", null, getRandomName(), getRandomState(),
				getRandomZip(), "US"), new Address("8 Yawkey Way", null,
				getRandomName(), getRandomState(), getRandomZip(), "US"),
				Collections.singletonMap("work", getRandomPhone()), new Date(
						getRandomDateInMillis()));
	}

	private static final String[] STATE_CODES = { "AL", "AK", "AS", "AZ", "AR",
			"CA", "CO", "CT", "DE", "OF", "DC", "FM", "FL", "GA", "GU", "HI",
			"ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA",
			"MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
			"NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC",
			"SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" };

	public static final String FILENAME = "contacts.csv";
}