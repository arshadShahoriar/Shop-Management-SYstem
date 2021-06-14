package entity;

import java.lang.*;

public class PurchaseInfo
{
    private String purchaseId;
	private String customerId;
	private String productId;
	private int quantity;
    private double amount;
    public PurchaseInfo(){}
	public PurchaseInfo(String purchaseId,String customerId,String productId,int quantity,double amount)
	{
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
	}
 
    public void setPurchaseId(String purchaseId)
	{ 
		this.purchaseId = purchaseId;
	}
    public void setCustomerId(String customerId)
	{ 
		this.customerId = customerId;
	}
    public void setProductId(String productId)
	{
		this.productId = productId;
	}
    public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
    public void setAmount(double amount)
	{
		this.amount = amount;
	}

    public String getPurchaseId()
	{
		return purchaseId;
	}
    public String getCustomerId()
	{
		return customerId;
	}
    public String getProductId()
	{
		return productId;
	}
    public int getQuantity()
	{
		return quantity;
	}
    public double getAmount()
	{
		return amount;
	}


 }