package com.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Database.UserDAO;



public class EditProfile extends HttpServlet
{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher rd=null;
		String id =request.getParameter("id");
		String username = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone  = request.getParameter("phone");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(id);
		list.add(username);
		list.add(gender);
		list.add(phone);
		list.add(email);
		list.add(role);
		
		
		boolean flag = UserDAO.updateProfile(list);
		
		if(flag)
		{
			
			rd=request.getRequestDispatcher("/JSP/viewprofile.jsp?no=1");
			rd.forward(request, resp);
		}
		else
		{
			
			rd=request.getRequestDispatcher("/JSP/edit_profile.jsp?no=1");
			rd.forward(request, resp);
		}
		
		
	}
}
