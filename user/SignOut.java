package com.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;

public class SignOut extends HttpServlet
{private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		HttpSession session = req.getSession();
		String uid = session.getAttribute("userid").toString();

		String userid = UserDAO.getUserid(uid);
		
		boolean flg = UserDAO.updateStatus(userid);
		
		//if(flg)
		//{
			String num = req.getParameter("no");
			if(num.trim().equals("1"))
			{
				resp.sendRedirect(req.getContextPath()+"?no=6");
			}	
		//}
		
		
		
	}

}
