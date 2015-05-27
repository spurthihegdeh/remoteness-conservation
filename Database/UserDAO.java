
package com.Database;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.support.DesEncryption;



public class UserDAO 
{
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static UserDAO userDAO=null;
	private UserDAO()
	{
		
	}
	public static UserDAO getInstance()
	{
		if(userDAO == null)
		{
			userDAO= new UserDAO();
		}
		return userDAO;
	}
	
	
	public static boolean ChangePass(String name,String pwd)
	{
		boolean flg=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "update m_user set u_password = '"+pwd+"' where u_login_id = '"+name+"' ";
			
		 int i=statement.executeUpdate(sql);
			
			if(i!=0)
			{
				flg=true;
			}
			 
			System.out.println("Password Updated Successfully......");
		}
		catch(Exception e)
		{
			System.out.println("Exception in --> "+ e);
		}
		return flg;
		
	}
	
	
	public static ResultSet getUserDetails(String id)
	{
		
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select *from m_user where u_id='"+id.trim()+"'";
			
			resultSet = statement.executeQuery(sql);
			
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return resultSet;
	}
	
	public static int getRequestStatus()
	{
		
		int j=0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select count(*) from m_optimal_location";
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				j=resultSet.getInt(1);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return j;
	}
	
	public static boolean updateLocation(int inti_code,int ucode,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="insert into m_optimal_location(initiator_id,u_code,latitude,logitude,status) values('"+inti_code+"','"+ucode+"','"+lat+"','"+lon+"','False')";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean insertFinallocation(double dis,double lat,double lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="insert into m_final_location(near_lat,near_lon,distance) values('"+lat+"','"+lon+"','"+dis+"')";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean truncateData()
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="TRUNCATE TABLE m_final_location";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean updateLocation(int inti_code,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="insert into m_location(u_code,u_latitude,u_logitude) values('"+inti_code+"','"+lat+"','"+lon+"')";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean updateOptiLocation(int inti_code,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_location set u_latitude='"+lat+"',u_logitude='"+lon+"' where u_code='"+inti_code+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean updateProfile(ArrayList<String> list)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_user set u_name='"+list.get(1).toString()+"',u_gender='"+list.get(2).toString()+"',u_phone_no='"+list.get(3).toString()+"',u_email_id='"+list.get(4).toString()+"' where u_login_id='"+list.get(0).toString()+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	
	public static boolean chkInitiator(int inti_code)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select *from m_location where u_code='"+inti_code+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	
	public static boolean checkUpdatecode(int inti_code,int ucode,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_optimal_location set u_code='"+ucode+"',latitude='"+lat+"',logitude='"+lon+"' where initiator_id='"+inti_code+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean UpdateFinalLocation(int inti_code,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_location set u_latitude='"+lat+"',u_logitude='"+lon+"' where u_code='"+inti_code+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	public static boolean UpdateFinalLocation1(int inti_code,String lat,String lon)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_optimal_location set latitude='"+lat+"',logitude='"+lon+"' where initiator_id='"+inti_code+"'";
			System.out.println(sql);
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	
	public static boolean checkIntiacode(int inti_code)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select *from m_optimal_location where initiator_id='"+inti_code+"'";
			System.out.println(sql);
			
			ResultSet i = statement.executeQuery(sql);
			
			while(i.next())
			{
			flag=true;	
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return flag;
	}
	
	
	public static int getIntiator_code()
	{
		
		int code=0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select u_code from m_user where role='Initiator'";
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				code = resultSet.getInt(1);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return code;
	}
	
	public static ArrayList<String> getFinalLocation()
	{
		
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select *from m_final_location where distance=(select min(distance) from m_final_location)";
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				list.add(resultSet.getString(2));
				list.add(resultSet.getString(3));
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return list;
	}
	
	
	public static int getIntiator_code(String eid)
	{
		
		int code=0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select u_code from m_user where role='Initiator' and u_email_id='"+eid+"'";
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				code = resultSet.getInt(1);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return code;
	}
	
	
	public static int getMem_code(String uid)
	{
		
		int code=0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select u_code from m_user where u_email_id='"+uid+"'";
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				code = resultSet.getInt(1);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return code;
	}
	
	
	public static int getCount()
	{
		
		int i=0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select count(*) from m_user";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				i=resultSet.getInt(1);
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getCount(): "+ e);
		}
		
		return i;
	}
	
	public static boolean updateStatus(String uid)
	{
		
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_user set u_status='False' where u_login_id='"+uid+"'";
			
			int i = statement.executeUpdate(sql);
			
			if(i!=0)
			{
				flag=true;
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->getUserid(String email): "+ e);
		}
		
		return flag;
	}
	
	
	public static String getUserid(String email)
	{
		
		String uid ="";
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select u_login_id from m_user where u_email_id='"+email.trim()+"'";
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				uid=resultSet.getString(1);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->getUserid(String email): "+ e);
		}
		
		return uid;
	}
	
	
	public static ArrayList<String> getLatLag()
	{
		DesEncryption encrypter = new DesEncryption("333kkk45");
		
		System.out.println("Start===== ");
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select a.u_latitude,a.u_logitude from m_location a,m_user b where a.u_code = b.u_code and b.u_status='True'";
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				
				String declat = encrypter.decrypt(resultSet.getString(1));
				
				String declon = encrypter.decrypt(resultSet.getString(2));
				
				list.add(declat+"~"+declon);
			}
			
			System.out.println("List :"+list);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return list;
	}
	
	public static ArrayList<String> getLatLagMem(int memcode)
	{
		
		DesEncryption encrypter = new DesEncryption("333kkk45");
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select a.u_latitude,a.u_logitude from m_location a,m_optimal_location b where a.u_code = b.initiator_id and b.u_code='"+memcode+"'";
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				
				String declat = encrypter.decrypt(resultSet.getString(1));
				
				String declon = encrypter.decrypt(resultSet.getString(2));
				
				list.add(declat+"~"+declon);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getLatLagMem(String userid): "+ e);
		}
		
		return list;
	}
	
	public static ArrayList<String> getLatLag(int initcode)
	{
		
		DesEncryption encrypter = new DesEncryption("333kkk45");
		
		
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="select latitude,logitude from m_optimal_location where initiator_id = '"+initcode+"'";
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				
				String declat = encrypter.decrypt(resultSet.getString(1));
				
				String declon = encrypter.decrypt(resultSet.getString(2));
				
				list.add(declat+"~"+declon);
			}
			
			System.out.println(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return list;
	}
	
	
	
	public static boolean updateuser(String userid,String unm,String email,String phone)
	{
		boolean flg=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "update m_user set u_name = '"+unm+"',u_email='"+email+"',u_phone='"+phone+"' where u_id = '"+userid+"' ";
			
		 int i=statement.executeUpdate(sql);
			
			if(i!=0)
			{
				flg=true;
			}
			 
			System.out.println("Password Updated Successfully......");
		}
		catch(Exception e)
		{
			System.out.println("Exception in --> "+ e);
		}
		return flg;
		
	}
	
	
	
	public static ResultSet getUsers()
	{
		
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select *from m_user");
		
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return resultSet;
	}
	
	public static ResultSet getProfile(String userid)
	{
		
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select *from m_user where u_login_id='"+userid+"'");
		
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserProcess-->getFiles(String userid): "+ e);
		}
		
		return resultSet;
	}
	
	
	
	public static boolean checkUser(String username,String password)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "select * from m_user where u_email_id='"+username+"' and u_password='"+password+"'";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=true;
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	public static String checkRole(String username)
	{
		String flag="";
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "select role from m_user where u_email_id='"+username+"' ";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=resultSet.getString(1);
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	public static int checkupdate(int icode)
	{
		int flag = 0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "select count(status) from m_optimal_location where initiator_id='"+icode+"' and status='True'";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=resultSet.getInt(1);
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkupdate(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	public static int checkupdatestatus(int icode)
	{
		int flag = 0;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "select count(initiator_id) from  m_optimal_location where initiator_id='"+icode+"'";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=resultSet.getInt(1);
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkupdate(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	
	
	public static boolean checkUser1(String username,String password)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "select * from m_user where u_login_id='"+username+"' and u_password='"+password+"'";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=true;
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	public static boolean updateLocation(String uid,String loti,String longi)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			String sql = "insert into m_location(u_code,u_latitude,u_logitude) values((select u_code from m_user where u_email_id='"+uid+"'),'"+loti+"','"+longi+"')";
			System.out.println(sql);
			int i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	
	
	public static boolean updateUserStatus(String username)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			int i = statement.executeUpdate("update m_user set u_status='True' where u_email_id='"+username+"'");
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->updateUserStatus(String userid): "+ flag);
		}
		return flag;
	}
	
	public static boolean updatePreferredlocation(int ucode,String lat,String longi)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="update m_optimal_location set status='True',latitude='"+lat+"',logitude='"+longi+"' where u_code='"+ucode+"'";
			System.out.println(sql);
			int i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->updateUserStatus(String userid): "+ flag);
		}
		return flag;
	}
	
	
	
	public static boolean checkAlreadyExist(String userid)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from m_user where u_login_id='"+userid+"'");
			while(resultSet.next())
			{
				flag=true;
			}
			System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	public static ResultSet selectMember()
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from m_user where role='Member'");
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return resultSet;
	}
	
	public boolean addUser(String userid,String password,String name,String gender,String phone,String email,String role)
	{
		boolean flag=false;
		try
		{
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			
			String sql="insert into m_user(u_login_id,u_password,u_name,u_gender,u_phone_no,u_email_id,u_status,role) values('"+userid+"','"+password+"','"+name+"','"+gender+"','"+phone+"','"+email+"','False','"+role+"')";
			
			int i=statement.executeUpdate(sql);
			
			if(i!=0)
			{
				flag=true;
			}
			
			
			//System.out.println("User Login Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserDAO-->checkUser(String userid,String pass): "+ e);
		}
		return flag;
	}
	
	
	public static boolean editProfile(String [] s) 
	{
		boolean flag=false;
		String sql = "";
		try
		{   
			DAO dao=DAO.getInstance();
			connection=dao.connector();
			statement = connection.createStatement();
			sql = "update m_user set u_name='"+s[1]+"',u_id='"+s[2]+"',u_email='"+s[3]+"',u_phone='"+s[4]+"' where u_no='"+s[0]+"'";
			//System.out.println("******* Edit User Profile Info *********\n");
			//System.out.println(sql);
			int i=statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			//System.out.println("User Edit Profile Status : "+flag);
		}
		catch(Exception e)
		{
			System.out.println("Opp's Error is in UserDAO-editProfile() :");
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
}
