package com.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.UserDAO;
import com.support.DesEncryption;
import com.support.DistanceCalculation;
import com.support.EuclideanDis;

public class FindFinalLocation extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher rd=null;
		HttpSession s = req.getSession();
		
		String uid = s.getAttribute("userid").toString();
		
		int icode = UserDAO.getIntiator_code(uid);
		
		
		String value = req.getParameter("nearbylocation");
		
		String main_latitude = req.getParameter("lat");
		
		String main_longitude = req.getParameter("longi");
		
			
		DistanceCalculation calculation = new DistanceCalculation();
		
		//ArrayList list = new ArrayList();
		
		System.out.println("main location latitude and longitude :"+main_latitude+"   "+main_longitude);
		
		String[] str = value.split("~");
		
		boolean f = UserDAO.truncateData();
		
		System.out.println("Test Status :"+f);
		
		for(int i=0;i<str.length;i++)
		{
			
			System.out.println("Splited Location is :"+str[i]);
			
			if(i==0)
			{
				
				System.out.println("**************** First Location ************");
				String near_latlon = str[i].substring(1);
				
				System.out.println("near_latlon 1 :"+near_latlon);
				
				near_latlon = near_latlon.substring(0,near_latlon.length()-1);
				
				System.out.println("near_latlon 2:"+near_latlon);
				
				String[] latlon = near_latlon.split(",");
				
				double latitude1 = Double.parseDouble(latlon[0]);
				
				double longitude1 = Double.parseDouble(latlon[1]);
				
				System.out.println("**************** Find Distance  ************");
				
				//double final_val = calculation.distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1, 'K');
				
				
				double final_val = EuclideanDis.Distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1);
				
				
				//System.out.println(calculation.distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1, 'K') + " Kilometers\n");
				
				//list.add(final_val);
				
				UserDAO.insertFinallocation(final_val, latitude1, longitude1);
				
			}
			else
			{
				
				String near_latlon = str[i].substring(2);
				
				System.out.println("near_latlon 3:"+near_latlon);
				
				
				near_latlon = near_latlon.substring(0,near_latlon.length()-1);
				
				System.out.println("near_latlon 4:"+near_latlon);
				
				String[] latlon = near_latlon.split(",");
				
				double latitude1 = Double.parseDouble(latlon[0]);
				
				double longitude1 = Double.parseDouble(latlon[1]);
				
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
				
				System.out.println("**************** Find Distance Calculation ************");
				
				//System.out.println(calculation.distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1, 'K') + " Kilometers\n");
				
				//double final_val = calculation.distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1, 'K');
				
				double final_val = EuclideanDis.Distance(Double.parseDouble(main_latitude), Double.parseDouble(main_longitude),latitude1,longitude1);
				
				
				//list.add(final_val);
				
				UserDAO.insertFinallocation(final_val, latitude1, longitude1);
			}
		}
		
		ArrayList<String> list = UserDAO.getFinalLocation();
		
		System.out.println("Final Location :"+list);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		
		
		boolean flag = UserDAO.UpdateFinalLocation(icode, list.get(0).toString(), list.get(1).toString());
		
		
		DesEncryption encrypter = new DesEncryption("333kkk45");
		// Encrypt
		String encrypted = encrypter.encrypt(list.get(0).toString());
		String enclon = encrypter.encrypt(list.get(1).toString());
		
		boolean flag1 = UserDAO.UpdateFinalLocation1(icode, encrypted, enclon);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		
		
		if(flag)
		{
			rd = req.getRequestDispatcher("JSP/find_optimallocation.jsp?no=1");
			rd.forward(req, resp);
		}
		else
		{
			rd = req.getRequestDispatcher("JSP/find_optimallocation.jsp?no=2");
			rd.forward(req, resp);
		}
		
		
	}

}
