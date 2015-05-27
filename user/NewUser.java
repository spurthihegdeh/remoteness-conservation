
package com.user;

import javax.servlet.RequestDispatcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.*;


public class NewUser extends HttpServlet
{private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			String userid=request.getParameter("id");
			String pass=request.getParameter("password");
			String name=request.getParameter("name");
			String sex = request.getParameter("gender");
			String email=request.getParameter("useremail");
			
			String pho_no=request.getParameter("mobile_phone");
			
			
			RequestDispatcher rd = null;
			String path = null;
			
			int ucount = UserDAO.getCount();
			if(ucount==0)
			{
				
				UserDAO userDao=UserDAO.getInstance();
				
				boolean f = UserDAO.checkAlreadyExist(userid);
				
				if(!f)
				{

					boolean result=userDao.addUser(userid, pass,name,sex,pho_no,email,"Initiator");
					if(result)
					{
							rd = request.getRequestDispatcher("index.jsp?no=1");
							rd.forward(request,response);
							
					}
					else
					{
						response.sendRedirect("JSP/registration.jsp?no=2");
					}	
				}
				else
				{
					
					rd = request.getRequestDispatcher("JSP/registration.jsp?no=1");
					rd.forward(request,response);
				}
				
			}
			else
			{
				UserDAO userDao=UserDAO.getInstance();
				
				boolean f = UserDAO.checkAlreadyExist(userid);
				
				if(!f)
				{

					boolean result=userDao.addUser(userid, pass,name,sex,pho_no,email,"Member");
					if(result)
					{
							rd = request.getRequestDispatcher("index.jsp?no=1");
							rd.forward(request,response);
							
					}
					else
					{
						response.sendRedirect("JSP/registration.jsp?no=2");
					}	
				}
				else
				{
					
					rd = request.getRequestDispatcher("JSP/registration.jsp?no=1");
					rd.forward(request,response);
				}
					
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
