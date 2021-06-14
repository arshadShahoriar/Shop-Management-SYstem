package interfaces;

import java.lang.*;

import entity.*;

public interface IProductRepo
{
	public void insertInDB(Product p);
	public void updateInDB(Product p);
	public Product searchProduct(String productId);
	public String[][] getAllProduct();
}