
package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Database.UserDAO;





public class ChangePass_User extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException 
	{
		PrintWriter out=response.getWriter();
		try
		{
			
			
				String id = request.getParameter("id");
				String old = request.getParameter("oldpass");
				String newp = request.getParameter("newpass");
				String confirm = request.getParameter("confirmpass");
				
				
				boolean result=UserDAO.checkUser1(id, old);
				if(result)
				{
					if(newp.equals(confirm))
					{
						result=UserDAO.ChangePass(id, confirm);
						if(result)
						{
							
							RequestDispatcher rd=request.getRequestDispatcher("/JSP/changepass.jsp?no=1");
							rd.forward(request, response);
						}
						else
						{
							RequestDispatcher rd=request.getRequestDispatcher("/JSP/changepass.jsp?no=2");
							rd.forward(request, response);
						}
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/JSP/changepass.jsp?no=3");
						rd.forward(request, response);
					}
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("/JSP/changepass.jsp?no=3");
					rd.forward(request, response);
				}
			
		}
		catch(Exception e)
		{
			System.out.println("Opps's Error is in Admin ChangePass Servlet......"+e);
			out.println("Opps's Error is in Admin ChangePass Servlet......"+e);
		}
	}
}
