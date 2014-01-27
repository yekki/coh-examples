package com.yekki.coh.wls.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NamedCache cache = CacheFactory.getCache("yekki-dist");
		cache.put("username", "Gary Niu");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>Welcome " + cache.get("username") + "!!</body></html>");
	}

}
