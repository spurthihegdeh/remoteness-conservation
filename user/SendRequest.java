package com.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.support.DesEncryption;

public class SendRequest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher rd=null;
		boolean f =false;
		
		ResultSet rs1 = UserDAO.selectMember();
		
		HttpSession session = req.getSession();
		String userid = session.getAttribute("userid").toString();
		
		int initiator_code = UserDAO.getIntiator_code();
		
		String sub = req.getParameter("submit");
		
		if(sub.trim().equals("get"))
		{
			String lati = req.getParameter("lat");
			String longi = req.getParameter("lon");
			
			boolean flag = UserDAO.chkInitiator(initiator_code);
			
			if(flag)
			{
			
				DesEncryption encrypter = new DesEncryption("333kkk45");
				// Encrypt
				String encrypted = encrypter.encrypt(lati);
				String enclon = encrypter.encrypt(longi);
				boolean flg = UserDAO.updateOptiLocation(initiator_code, encrypted, enclon);
				
				req.setAttribute("rs", rs1);
				rd = req.getRequestDispatcher("JSP/select_group.jsp");
				rd.forward(req, resp);
			}
			else
			{
				DesEncryption encrypter = new DesEncryption("333kkk45");
				// Encrypt
				String encrypted = encrypter.encrypt(lati);
				String enclon = encrypter.encrypt(longi);
				
				boolean flg = UserDAO.updateLocation(initiator_code, encrypted, enclon);
				
				req.setAttribute("rs", rs1);
				rd = req.getRequestDispatcher("JSP/select_group.jsp");
				rd.forward(req, resp);
				
			}
			
			
		}
		else
		{
			String lati = req.getParameter("lat");
			String longi = req.getParameter("lon");
			
			
			String[] ucode = req.getParameterValues("code");
			System.out.println("Ucode Length :"+ucode);
			
			if(ucode==null)
			{
				req.setAttribute("rs", rs1);
				rd = req.getRequestDispatcher("JSP/select_group.jsp?no=1");
				rd.forward(req, resp);
			}
			else if(ucode.length<3)
			{
				req.setAttribute("rs", rs1);
				rd = req.getRequestDispatcher("JSP/select_group.jsp?no=4");
				rd.forward(req, resp);
			}
			else
			{
				
				System.out.println("User have Selected");
				
				boolean code_exist = UserDAO.checkIntiacode(initiator_code);
				
				if(code_exist)
				{
					
					DesEncryption encrypter = new DesEncryption("333kkk45");
					// Encrypt
					String encrypted = encrypter.encrypt(lati);
					String enclon = encrypter.encrypt(longi);
					
					java.util.List<String> list = Arrays.asList(ucode);
					
					for(int j=0;j<list.size();j++)
					{
						
						
							int user_code = Integer.parseInt(list.get(j));
							
							f = UserDAO.checkUpdatecode(initiator_code, user_code, encrypted,enclon);
						
					}
				}
				else
				{
					DesEncryption encrypter = new DesEncryption("333kkk45");
					// Encrypt
					String encrypted = encrypter.encrypt(lati);
					String enclon = encrypter.encrypt(longi);
					
					java.util.List<String> list = Arrays.asList(ucode);
					
					for(int j=0;j<list.size();j++)
					{
						
						
						int user_code = Integer.parseInt(list.get(j));
						
						f = UserDAO.updateLocation(initiator_code, user_code, encrypted,enclon);
						
						
					}
					
					/*
					
					for(int i=0;i<ucode.length;i++)
					{
						
						int user_code = Integer.parseInt(ucode[i]);
						
						f = UserDAO.updateLocation(initiator_code, user_code, encrypted,enclon);
						
						
						
					}*/
				}
				
				
				
				if(f)
				{
				req.setAttribute("rs", rs1);
				rd=req.getRequestDispatcher("JSP/select_optimal_location.jsp?no=2")	;
				rd.forward(req, resp);	
				}
				else
				{
					req.setAttribute("rs", rs1);
					rd=req.getRequestDispatcher("JSP/select_group.jsp?no=3")	;
					rd.forward(req, resp);	
				}
			}
		}
		
		
		
	}
}
