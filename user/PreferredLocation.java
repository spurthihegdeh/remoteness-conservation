package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;

public class PreferredLocation extends HttpServlet
{private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
		
		HttpSession s = req.getSession();
		
		String uid = s.getAttribute("userid").toString();
		
		int icode = UserDAO.getIntiator_code(uid);
		
		int ustatus = UserDAO.checkupdate(icode);
		
		int totalstatus = UserDAO.checkupdatestatus(icode);
		
		RequestDispatcher rd=null;
		
		if(ustatus==totalstatus)
		{
			String num = req.getParameter("no");
			
			if(num.equals("3"))
			{
				rd = req.getRequestDispatcher("JSP/find_optimallocation.jsp");
				rd.forward(req, resp);
				
			}
			else
			{
				
				rd = req.getRequestDispatcher("JSP/multiple_marker.jsp");
				rd.forward(req, resp);
			}
		}
		else
		{
			System.out.println("Satus Not yet Complete");
			rd = req.getRequestDispatcher("JSP/Member/pending_status.jsp");
			rd.forward(req, resp);
		}
		
		
		
	}
}
