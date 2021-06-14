package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class PurchaseInfoFrame extends JFrame implements ActionListener
{
	private JLabel purchaseIdLabel,customerIdLabel,productIdLabel,quantityLabel,amountLabel;
	private JTextField purchaseIdTF,customerIdTF,productIdTF,quantityTF,amountTF;
	private JButton loadBtn, insertBtn, updateBtn, refreshBtn, getAllBtn, backBtn,logoutBtn;
	private JPanel panel;
	private JTable purchaseInfoTable;
	private JScrollPane purchaseInfoTableSP;
	private PurchaseInfoRepo pr;
	private User user;
	
	public PurchaseInfoFrame(User user)
	{
		super("PurchaseInfoFrame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		this.user = user;
		
		pr = new PurchaseInfoRepo();
		
		String data[][] = {{"", "", "", "",""}};
		
		String head[] = {"PurchaseId", "CustomerId", "ProductId", "Quantity","Amount"};
		
		purchaseInfoTable = new JTable(data,head);
		purchaseInfoTableSP = new JScrollPane(purchaseInfoTable);
		purchaseInfoTableSP.setBounds(350, 50, 400, 200);
		purchaseInfoTable.setEnabled(false);
		panel.add(purchaseInfoTableSP);
		
		purchaseIdLabel = new JLabel("PurchaseId:");
		purchaseIdLabel.setBounds(100,50,100,30);
		panel.add(purchaseIdLabel);
		
		purchaseIdTF = new JTextField();
		purchaseIdTF.setBounds(220,50,100,30);
		panel.add(purchaseIdTF);
		
		customerIdLabel = new JLabel("CustomerId:");
		customerIdLabel.setBounds(100,100,100,30);
		panel.add(customerIdLabel);
		
		customerIdTF = new JTextField();
		customerIdTF.setBounds(220,100,100,30);
		panel.add(customerIdTF);
		
		productIdLabel = new JLabel("ProductId:");
		productIdLabel.setBounds(100,150,100,30);
		panel.add(productIdLabel);
		
		productIdTF = new JTextField();
		productIdTF.setBounds(220,150,100,30);
		panel.add(productIdTF);
		
		quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(100,200,100,30);
		panel.add(quantityLabel);
		
		quantityTF = new JTextField();
		quantityTF.setBounds(220,200,100,30);
		panel.add(quantityTF);
		
		amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(100,250,100,30);
		panel.add(amountLabel);
		
		amountTF = new JTextField();
		amountTF.setBounds(220,250,100,30);
		panel.add(amountTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		insertBtn.addActionListener(this);
		insertBtn.setEnabled(false);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(400,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,300,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		logoutBtn = new JButton("LogOut");
		logoutBtn.setBounds(700,300,80,30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(quantityTF.getText().equals("") || quantityTF.getText().equals(null))
			{
				PurchaseInfo p = new PurchaseInfo();
				
				p = pr.searchPurchaseInfo(purchaseIdTF.getText());
				
				if(p!= null)
				{
					purchaseIdTF.setText(p.getPurchaseId());
					customerIdTF.setText(p.getCustomerId());
					productIdTF.setText(p.getProductId());
					quantityTF.setText(p.getQuantity()+"");
					amountTF.setText(p.getAmount()+"");
					
					purchaseIdTF.setEnabled(false);
					customerIdTF.setEnabled(false);
					productIdTF.setEnabled(false);
					quantityTF.setEnabled(true);
					amountTF.setEnabled(false);
					
					updateBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					//loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
			
			else
			{
				String productId = productIdTF.getText();
				int quantity = Integer.parseInt(quantityTF.getText());
				amountTF.setText(pr.getAmountValue(productId,quantity)+"");
				if(user.getStatus() != 2)
				{
					insertBtn.setEnabled(true);
				}
				else{}
				refreshBtn.setEnabled(true);
			}
		
		}
		else if(command.equals(insertBtn.getText()))
		{
			PurchaseInfo pf = new PurchaseInfo();
			
			pf.setPurchaseId(purchaseIdTF.getText());
			pf.setCustomerId(customerIdTF.getText());
			pf.setProductId(productIdTF.getText());
			pf.setQuantity(Integer.parseInt(quantityTF.getText()));
			pf.setAmount(Double.parseDouble(amountTF.getText()));
			
			
			pr.insertInDB(pf);
			
			JOptionPane.showMessageDialog(this, "Inserted");
			
			purchaseIdTF.setText("");
			customerIdTF.setText("");
			productIdTF.setText("");
			quantityTF.setText("");
			amountTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			purchaseIdTF.setText("");
			customerIdTF.setText("");
			productIdTF.setText("");
			quantityTF.setText("");
			amountTF.setText("");
			
			purchaseIdTF.setEnabled(true);
			customerIdTF.setEnabled(true);
			productIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			PurchaseInfo pf = new PurchaseInfo();
			
			String productId = productIdTF.getText();
			int quantity = Integer.parseInt(quantityTF.getText());
			amountTF.setText(pr.getAmountValue(productId,quantity)+"");
			
			pf.setPurchaseId(purchaseIdTF.getText());
			pf.setCustomerId(customerIdTF.getText());
			pf.setProductId(productIdTF.getText());
			pf.setQuantity(quantity);
			pf.setAmount(Double.parseDouble(amountTF.getText()));
			
			pr.updateInDB(pf);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			purchaseIdTF.setText("");
			customerIdTF.setText("");
			productIdTF.setText("");
			quantityTF.setText("");
			amountTF.setText("");
			
			purchaseIdTF.setEnabled(true);
			customerIdTF.setEnabled(true);
			productIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = pr.getAllPurchaseInfo();
			String head[] = {"PurchaseId", "CustomerId", "ProductId", "Quantity","Amount"};
			
			panel.remove(purchaseInfoTableSP);
			
			purchaseInfoTable = new JTable(data,head);
			purchaseInfoTable.setEnabled(false);
			purchaseInfoTableSP = new JScrollPane(purchaseInfoTable);
			purchaseInfoTableSP.setBounds(350, 50, 400, 200);
			panel.add(purchaseInfoTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
			if(user.getStatus()==2)
			{
				CustomerHome ch = new CustomerHome(user);
				ch.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				EmployeeHome eh = new EmployeeHome(user);
				eh.setVisible(true);
				this.setVisible(false);
			}
		}
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}	
}