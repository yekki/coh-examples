package com.yekki.coh.simple;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class Main {

	public static final String CACHE_NAME = "yekki-near";
	public static void main(String[] args) {
		
		NamedCache cache = CacheFactory.getCache(CACHE_NAME);
		
		cache.put("username", "Gary");
		
		System.out.println("########## " + cache.get("username") + " ##########");
		
		CacheFactory.shutdown();
	}

}
