package entity;

import java.lang.*;

public class Customer
{
	private String customerId;
	private String customerName;
	private String address;
	private String phoneNo;
	
	public Customer(){}
	public Customer(String customerId,String customerName,String address,String phoneNo)
	{
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	public String getCustomerId()
	{
		return customerId;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public String getAddress()
	{
		return address;
	}
	public String getPhoneNo()
	{
		return phoneNo;
	}
}