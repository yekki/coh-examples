package com.oracle.handson;

import java.util.Iterator;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.aggregator.Count;
import com.tangosol.util.aggregator.DoubleAverage;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.aggregator.LongMin;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.extractor.KeyExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.AlwaysFilter;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.GreaterFilter;
import com.tangosol.util.filter.LikeFilter;
import com.tangosol.util.filter.NotEqualsFilter;

public class QueryExample {

	public static void main(String[] args) {
		NamedCache cache = CacheFactory.getCache("ContactsCache");
		query(cache);
	}

	@SuppressWarnings("rawtypes")
	public static void query(NamedCache cache) {
		// Add indexes to make queries more efficient
		ReflectionExtractor reflectAddrHome = new ReflectionExtractor(
				"getHomeAddress");

		// Add an index for the age
		cache.addIndex(new ReflectionExtractor("getAge"), true, null);

		// Add index for state within home address
		cache.addIndex(new ChainedExtractor(reflectAddrHome,
				new ReflectionExtractor("getState")), true, null);

		// Add index for state within work address
		cache.addIndex(new ChainedExtractor(new ReflectionExtractor(
				"getWorkAddress"), new ReflectionExtractor("getState")), true,
				null);

		// Add index for city within home address
		cache.addIndex(new ChainedExtractor(reflectAddrHome,
				new ReflectionExtractor("getCity")), true, null);

		// Find all contacts who live in Massachusetts
		Set setResults = cache.entrySet(new EqualsFilter(
				"getHomeAddress.getState", "MA"));
		printResults("MA Residents", setResults);

		// Find all contacts who live in Massachusetts and work elsewhere
		setResults = cache.entrySet(new AndFilter(new EqualsFilter(
				"getHomeAddress.getState", "MA"), new NotEqualsFilter(
				"getWorkAddress.getState", "MA")));
		printResults("MA Residents, Work Elsewhere", setResults);

		// Find all contacts whose city name begins with 'S'
		setResults = cache.entrySet(new LikeFilter("getHomeAddress.getCity",
				"S%"));
		printResults("City Begins with S", setResults);

		final int nAge = 42;
		// Find all contacts who are older than nAge
		setResults = cache.entrySet(new GreaterFilter("getAge", nAge));
		printResults("Age > " + nAge, setResults);

		// Find all contacts with last name beginning with 'S' that live
		// in Massachusetts. Uses both key and value in the query.
		setResults = cache.entrySet(new AndFilter(new LikeFilter(
				new KeyExtractor("getLastName"), "S%", (char) 0, false),
				new EqualsFilter("getHomeAddress.getState", "MA")));
		printResults("Last Name Begins with S and State Is MA", setResults);
		
		   // Count contacts who are older than nAge
        System.out.println("count > " + nAge + ": "+ cache.aggregate(
                new GreaterFilter("getAge", nAge), new Count()));
  
        // Find minimum age
        System.out.println("min age: " + cache.aggregate(AlwaysFilter.INSTANCE,
                new LongMin("getAge")));
  
        // Calculate average age
        System.out.println("avg age: " + cache.aggregate(AlwaysFilter.INSTANCE,
                new DoubleAverage("getAge")));
  
        // Find maximum age
        System.out.println("max age: " + cache.aggregate(AlwaysFilter.INSTANCE,
                new LongMax("getAge")));
	}

	@SuppressWarnings("rawtypes")
	private static void printResults(String sTitle, Set setResults) {
		System.out.println(sTitle);
		for (Iterator iter = setResults.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}
}