package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ResearchersApi
 */
@WebServlet("/ResearchersApi")
public class ResearchersApi extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	Researchers regObj = new Researchers();
	  
	    public ResearchersApi() {
	        super();

	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String output = regObj.insertItem(request.getParameter("Name"),
					request.getParameter("Address"),
					request.getParameter("email"),
					request.getParameter("password"),
					request.getParameter("ConNum"));
					response.getWriter().write(output);
		}

		private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				
				String queryString = scanner.hasNext() ?
				scanner.useDelimiter("\\A").next() : "";
				
				scanner.close();
				
				String[] params = queryString.split("&");
				
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e)
			{
			}
				return map;
			}
		
		
		
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map paras = getParasMap(request);
			
			String output = regObj.updateItem(paras.get("hidItemIDSave").toString(),
			paras.get("Name").toString(),
			paras.get("Address").toString(),
			paras.get("email").toString(),
			paras.get("password").toString(),
			paras.get("ConNum").toString());
			response.getWriter().write(output);
		}

		/**
		 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
		 */
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map paras = getParasMap(request);
			
			String output = regObj.deleteItem(paras.get("accountID").toString());
			
			response.getWriter().write(output);
		}


}
