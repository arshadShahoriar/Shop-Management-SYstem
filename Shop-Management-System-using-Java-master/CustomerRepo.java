package repository;

import java.lang.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class CustomerRepo implements ICustomerRepo
{
	DatabaseConnection dbc;
	
	public CustomerRepo()
	{
		dbc = new DatabaseConnection();
	}
	public void insertInDB(Customer c)
	{
		String query = "INSERT INTO customer VALUES ('"+c.getCustomerId()+"','"+c.getCustomerName()+"','"+c.getAddress()+"',"+c.getPhoneNo()+");";
		
		try
		{
			
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void updateInDB(Customer c)
	{
		String query = "UPDATE customer SET customerName='"+c.getCustomerName()+"', address = '"+c.getAddress()+"', phoneNo = "+c.getPhoneNo()+" WHERE customerId='"+c.getCustomerId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	
	public Customer searchCustomer(String customerId)
	{
		Customer cu = null;
		String query = "SELECT customerName, address, phoneNo FROM customer WHERE customerId ='"+customerId+"';";     
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String customerName = dbc.result.getString("customerName");
				String address = dbc.result.getString("address");
				String phoneNo = dbc.result.getString("phoneNo");
				
				cu = new Customer();
				cu.setCustomerId(customerId);
				cu.setCustomerName(customerName);
				cu.setAddress(address);
				cu.setPhoneNo(phoneNo);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return cu;
	}
	
	public String[][] getAllCustomer()
	{
		ArrayList<Customer> ar = new ArrayList<Customer>();
		String query = "SELECT * FROM customer;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String customerId = dbc.result.getString("customerId");
				String customerName = dbc.result.getString("customerName");
				String address = dbc.result.getString("address");
				String phoneNo = dbc.result.getString("phoneNo");
				
				Customer cu = new Customer(customerId,customerName,address,phoneNo);
				ar.add(cu);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Customer)obj[i]).getCustomerId();
			data[i][1] = ((Customer)obj[i]).getCustomerName();
			data[i][2] = ((Customer)obj[i]).getAddress();
			data[i][3] = ((Customer)obj[i]).getPhoneNo();
		}
		return data;
	}
}