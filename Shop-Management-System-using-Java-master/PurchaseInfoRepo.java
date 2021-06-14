package repository;

import java.lang.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class PurchaseInfoRepo implements IPurchaseRepo
{
	
	DatabaseConnection dbc;
	
    public PurchaseInfoRepo()
	{
		dbc =  new DatabaseConnection();
	}
	
	public void insertInDB(PurchaseInfo p)
	{
		
		String query = "INSERT INTO purchaseInfo VALUES ('"+p.getPurchaseId()+"','"+p.getCustomerId()+"','"+p.getProductId()+"','"+p.getQuantity()+"',"+p.getAmount()+");";
		
	    try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex)
		{
			
			System.out.println(ex.getMessage());
		
		}
	
	  
	}
	  
	public void updateInDB(PurchaseInfo p)
	{
		
		String query = "UPDATE purchaseInfo SET customerId ='"+p.getCustomerId()+"', productId = '"+p.getProductId()+"', quantity = '"+p.getQuantity()+"',  amount = "+p.getAmount()+" WHERE purchaseId='"+p.getPurchaseId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		
	}
	
	public PurchaseInfo searchPurchaseInfo(String id){
		PurchaseInfo p = null;
		
		String query="SELECT customerId,productId,quantity,amount FROM purchaseInfo WHERE purchaseId ='"+id+"';"; 
		
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				String productId = dbc.result.getString("productId");
				String customerId = dbc.result.getString("customerId");
				int quantity=dbc.result.getInt("quantity");
				double amount = dbc.result.getDouble("amount");
				
				
				p = new PurchaseInfo();
				p.setPurchaseId(id);
				p.setCustomerId(customerId);
				p.setProductId(productId);
				p.setQuantity(quantity);
				p.setAmount(amount);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return p;
	}
	public String[][] getAllPurchaseInfo()
	{
		
		ArrayList<PurchaseInfo> ar = new ArrayList<PurchaseInfo>();
		String query = "SELECT * FROM purchaseInfo;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String purchaseId = dbc.result.getString("purchaseId");
				String customerId = dbc.result.getString("customerId");
				String productId = dbc.result.getString("productId");
				double amount = dbc.result.getDouble("amount");
				int quantity=dbc.result.getInt("quantity");
				
				PurchaseInfo e = new PurchaseInfo(purchaseId,customerId,productId,quantity,amount);
				ar.add(e);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][5];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((PurchaseInfo)obj[i]).getPurchaseId();
			data[i][1] = ((PurchaseInfo)obj[i]).getCustomerId();
			data[i][2] = ((PurchaseInfo)obj[i]).getProductId();
			data[i][3] = (((PurchaseInfo)obj[i]).getQuantity())+"";
			data[i][4] = (((PurchaseInfo)obj[i]).getAmount())+"";
		}
		return data;
	}
	
	public double getAmountValue(String productId,int quantity)
	{
		int availableQuantity =0;
		double price,amount=0.0;
		String query1 = "SELECT price, availableQuantity FROM product WHERE productId = '"+productId+"';";
		
		try
		{
			System.out.println(query1);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query1);
				
			while(dbc.result.next())
			{
				price = dbc.result.getDouble("price");
				availableQuantity = dbc.result.getInt("availableQuantity");
				amount = price * quantity;
				availableQuantity -= quantity;
			}
					
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
				
		dbc.closeConnection();
			
		String query2 = "UPDATE product SET availableQuantity ='"+availableQuantity +"'WHERE productId ='"+productId+"';";
				
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query2);
			dbc.closeConnection();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return amount;
	}
}