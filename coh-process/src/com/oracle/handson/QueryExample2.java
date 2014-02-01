package com.oracle.handson;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;

import static com.tangosol.util.QueryHelper.*;

import com.tangosol.util.aggregator.Count;
import com.tangosol.util.aggregator.DoubleAverage;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.aggregator.LongMin;

import java.util.Iterator;
import java.util.Set;

public class QueryExample2 {

	public static void main(String[] args) {
		NamedCache cache = CacheFactory.getCache("ContactsCache");
		query(cache);
	}

	@SuppressWarnings("rawtypes")
	public static void query(NamedCache cache) {
		
		cache.addIndex(createExtractor("age"), true, null);
		cache.addIndex(createExtractor("homeAddress.state"), false, null);
		cache.addIndex(createExtractor("workAddress.state"), false, null);
		cache.addIndex(createExtractor("homeAddress.city"), true, null);
		Set setResults = cache
				.entrySet(createFilter("homeAddress.state = 'MA'"));
		printResults("MA Residents", setResults);

		setResults = cache
				.entrySet(createFilter("homeAddress.state is 'MA' and workAddress is not 'MA'"));
		printResults("MA Residents, Work Elsewhere", setResults);

		setResults = cache.entrySet(createFilter("homeAddress.city like 'S%'"));
		printResults("City Begins with S", setResults);

		final int nAge = 42;
		Object[] aEnv = new Object[] { new Integer(nAge) };
		
		setResults = cache.entrySet(createFilter("age > ?1", aEnv));
		printResults("Age > " + nAge, setResults);

		setResults = cache
				.entrySet(createFilter("key(lastName) like 'S%' and homeAddress.state = 'MA'"));
		setResults = cache
				.entrySet(createFilter("key().lastName like 'S%' and homeAddress.state = 'MA'"));
		printResults("Last Name Begins with S and State Is MA", setResults);

		System.out.println("count > " + nAge + ": "
				+ cache.aggregate(createFilter("age > ?1", aEnv), new Count()));

		Filter always = createFilter("true");
		System.out.println("min age: "
				+ cache.aggregate(always, new LongMin("getAge")));

		System.out.println("avg age: "
				+ cache.aggregate(always, new DoubleAverage("getAge")));

		System.out.println("max age: "
				+ cache.aggregate(always, new LongMax("getAge")));

		System.out.println("------QueryLanguageExample completed------");

	}


	@SuppressWarnings("rawtypes")
	private static void printResults(String sTitle, Set setResults) {
		System.out.println(sTitle);
		for (Iterator iter = setResults.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}
}