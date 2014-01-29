package com.yekki.coh.cachestore;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class ReadDataClient {
	private static final int EMP_ID = 1;
	
	public static void main(String[] args) {
		
		NamedCache employeeCache = CacheFactory.getCache("Employee");
		System.out.println("Read Employee from cache is: "
				+ employeeCache.get(EMP_ID));
	}
}
