package com.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;
import com.support.DesEncryption;

public class UpdatePreferredLocation extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
		
		RequestDispatcher rd=null;
		HttpSession s = req.getSession();
		
		String uid = s.getAttribute("userid").toString();
		
		
		int code = UserDAO.getMem_code(uid);
		
		String lati = req.getParameter("lat");
		String longi = req.getParameter("lon");
		
		DesEncryption encrypter = new DesEncryption("333kkk45");
		// Encrypt
		String encrypted = encrypter.encrypt(lati);
		String enclon = encrypter.encrypt(longi);
		
		boolean flg = UserDAO.updatePreferredlocation(code,encrypted, enclon);
		if(flg)
		{
			
			rd = req.getRequestDispatcher("JSP/Member/select_optimal_location.jsp?no=1");
			rd.forward(req, resp);
		}
		else
		{
		
			rd = req.getRequestDispatcher("JSP/Member/select_optimal_location.jsp?no=2");
			rd.forward(req, resp);
		}
		
		
		
		
	}
}
