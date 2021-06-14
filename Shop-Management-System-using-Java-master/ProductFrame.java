package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;


public class ProductFrame extends JFrame implements ActionListener
{
	private JLabel productIdLabel, productNameLabel, priceLabel, availableQuantityLabel;
	private JTextField productIdTF, productNameTF, priceTF, availableQuantityTF;
	private JButton loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, getAllBtn, backBtn,logoutBtn;
	private JPanel panel;
	private JTable productTable;
	private JScrollPane productTableSP;
	
	private User user;
	private ProductRepo pr;
	
	
	public ProductFrame(User user)
	{
		super("Product Frame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		pr = new ProductRepo();
		
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"productId", "productName", "Price", "AvailableQuantity"};
		
		productTable = new JTable(data,head);
		productTableSP = new JScrollPane(productTable);
		productTableSP.setBounds(350, 100, 400, 150);
		productTable.setEnabled(false);
		panel.add(productTableSP);
		
		productIdLabel = new JLabel("ID :");
		productIdLabel.setBounds(100,100,100,30);
		panel.add(productIdLabel);
		
		productIdTF = new JTextField();
		productIdTF.setBounds(220,100,100,30);
		panel.add(productIdTF);
		
		productNameLabel = new JLabel("Name :");
		productNameLabel.setBounds(100,150,100,30);
		panel.add(productNameLabel);
		
		productNameTF = new JTextField();
		productNameTF.setBounds(220,150,100,30);
		panel.add(productNameTF);
		
		priceLabel = new JLabel("Price: ");
		priceLabel.setBounds(100,200,100,30);
		panel.add(priceLabel);
		
		priceTF = new JTextField();
		priceTF.setBounds(220,200,100,30);
		panel.add(priceTF);
		
		availableQuantityLabel = new JLabel("AvailableQuantity: ");
		availableQuantityLabel.setBounds(100,250,100,30);
		panel.add(availableQuantityLabel);
		
		availableQuantityTF = new JTextField();
		availableQuantityTF.setBounds(220,250,100,30);
		panel.add(availableQuantityTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		insertBtn.addActionListener(this);
		if(user.getStatus()==2)
		{
			insertBtn.setEnabled(false);
		}
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
			if(!productIdTF.getText().equals("") || !productIdTF.getText().equals(null))
			{
				Product p = pr.searchProduct(productIdTF.getText());
				if(p!= null)
				{
					productNameTF.setText(p.getProductName());
					priceTF.setText(p.getPrice()+"");
					availableQuantityTF.setText(p.getAvailableQuantity()+"");
					
					productIdTF.setEnabled(false);
					if(user.getStatus()==2)
					{
						productNameTF.setEnabled(false);
						priceTF.setEnabled(false);
						availableQuantityTF.setEnabled(false);
						updateBtn.setEnabled(false);
					    refreshBtn.setEnabled(true);
					    insertBtn.setEnabled(false);
					    loadBtn.setEnabled(false);
					}
					else
					{
						productNameTF.setEnabled(true);
						priceTF.setEnabled(true);
						availableQuantityTF.setEnabled(true);
						updateBtn.setEnabled(true);
						refreshBtn.setEnabled(true);
						insertBtn.setEnabled(false);
						loadBtn.setEnabled(false);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(insertBtn.getText()))
		{
			Product p = new Product();
			
			p.setProductId(productIdTF.getText());
			p.setProductName(productNameTF.getText());
			p.setPrice(Double.parseDouble(priceTF.getText()));
			p.setAvailableQuantity(Integer.parseInt(availableQuantityTF.getText()));
			
			
			pr.insertInDB(p);
			
			JOptionPane.showMessageDialog(this, "Inserted");
			
			productIdTF.setText("");
			productNameTF.setText("");
			priceTF.setText("");
			availableQuantityTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			productIdTF.setText("");
			productNameTF.setText("");
			priceTF.setText("");
			availableQuantityTF.setText("");
			
			productIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			if(user.getStatus()==2)
			{
				insertBtn.setEnabled(false);
			}
			else
			{
				insertBtn.setEnabled(true);
			}
		}
		else if(command.equals(updateBtn.getText()))
		{
			Product p = new Product();
			
			p.setProductId(productIdTF.getText());
			p.setProductName(productNameTF.getText());
			try
			{
				p.setPrice(Double.parseDouble(priceTF.getText()));
				p.setAvailableQuantity(Integer.parseInt(availableQuantityTF.getText()));
			}
			catch(Exception ex){System.out.println(ex.getMessage());}
			pr.updateInDB(p);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			productIdTF.setText("");
			productNameTF.setText("");
			priceTF.setText("");
			availableQuantityTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			productIdTF.setEnabled(true);
			productNameTF.setEnabled(true);
			priceTF.setEnabled(true);
			availableQuantityTF.setEnabled(true);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = pr.getAllProduct();
			String head[] = {"productId", "productName", "Price", "AvailableQuantity"};
			
			panel.remove(productTableSP);
			
			productTable = new JTable(data,head);
			productTable.setEnabled(false);
			productTableSP = new JScrollPane(productTable);
			productTableSP.setBounds(350, 100, 400, 150);
			panel.add(productTableSP);
			
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