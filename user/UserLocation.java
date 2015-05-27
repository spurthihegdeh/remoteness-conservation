package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;

public class UserLocation extends HttpServlet
{private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd =null;
		HttpSession s = req.getSession();
		
		String uid = s.getAttribute("userid").toString();
		
		String loti = req.getParameter("latitude");
		
		String longi = req.getParameter("longitude");
		
		System.out.println("latitude :"+loti);
		System.out.println("longitude :"+longi);
		
		boolean flag = UserDAO.updateLocation(uid,loti, longi);
		
		if(flag)
		{
			
			rd = req.getRequestDispatcher("JSP/userhome.jsp?no=1");
			rd.forward(req,resp);
		}
		else
		{
			rd = req.getRequestDispatcher("JSP/userhome.jsp?no=2");
			rd.forward(req,resp);	
		}
		
	}
}
