package repository;

import java.lang.*;

import entity.*;
import interfaces.*;

public class UserRepo implements IUserRepo
{
	DatabaseConnection dbc;
	
	public UserRepo()
	{
		dbc = new DatabaseConnection();
	}
	public User getUser(String userId, String pass)
	{
		User user = null;
		String query = "SELECT userId, password, status FROM login WHERE userId='"+userId+"' AND password='"+pass+"';";
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				user = new User();
				user.setUserId(dbc.result.getString("userId"));
				user.setPassword(dbc.result.getString("password"));
				user.setStatus(dbc.result.getInt("status"));
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		dbc.closeConnection();
		return user;
	}
	public void insertUser(User u)
	{
		String query = "INSERT INTO login VALUES ('"+u.getUserId()+"','"+u.getPassword()+"',"+u.getStatus()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void updateUser(User u)
	{
		String query = "UPDATE login SET password ='"+u.getPassword()+"', status ="+u.getStatus()+" WHERE userId='"+u.getUserId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteUser(String userId)
	{
		String query = "DELETE from login  WHERE userId='"+userId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public String getPass(String userId)
	{
		String query1 = "SELECT  password FROM login WHERE userId = '"+userId+"';";
		String pass = null;
		try
		{
			System.out.println(query1);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query1);
				
			while(dbc.result.next())
			{
				pass = dbc.result.getString("password");
			}
					
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
				
		dbc.closeConnection();
		return pass;
	}
}