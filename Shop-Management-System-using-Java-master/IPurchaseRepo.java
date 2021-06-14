package interfaces;

import java.lang.*;

import entity.*;

public interface IPurchaseRepo
{
    public void insertInDB(PurchaseInfo p);
	public void updateInDB(PurchaseInfo p);
	public PurchaseInfo searchPurchaseInfo(String id);
	public String[][] getAllPurchaseInfo();
}