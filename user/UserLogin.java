
package com.user;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;


public class UserLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		RequestDispatcher rd=null;
		
			HttpSession s = request.getSession();
			
			
			String uid = request.getParameter("email");
			
			String pass = request.getParameter("password");
			
			boolean f = UserDAO.checkUser(uid,pass);
			
			if(f)
			{
				boolean flag = UserDAO.updateUserStatus(uid);
				
				String role = UserDAO.checkRole(uid);
				
				if(role.equals("Initiator"))
				{
					
					try
					{
					s.setAttribute("role", "Initiator");
					s.setAttribute("userid", uid);
					
					rd = request.getRequestDispatcher("JSP/userhome.jsp");
					rd.forward(request, response);
					
					}
					catch (ServletException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					
					try
					{
					s.setAttribute("role", "Member");
						
					s.setAttribute("userid", uid);
					rd = request.getRequestDispatcher("JSP/userhome1.jsp");
					rd.forward(request, response);
					
					}
					catch (ServletException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
			}
			else
			{
				try
				{
				rd = request.getRequestDispatcher("index.jsp?no=2");
				rd.forward(request, response);
				}
				catch (ServletException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
	}
}
