package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class CustomerFrame extends JFrame implements ActionListener
{
	private JLabel customerIdLabel,customerNameLabel,addressLabel,phoneNoLabel;
	private JTextField  customerIdTF,customerNameTF,addressTF,phoneNoTF;
	private JButton loadBtn, updateBtn, refreshBtn, getAllBtn, backBtn,logoutBtn;
	private JPanel panel;
	private JTable customerTable;
	private JScrollPane customerTableSP; 
	private User user;
	private CustomerRepo cr;
	
	public CustomerFrame(User user)
	{
		super("CustomerFrame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		cr = new CustomerRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Address", "Phobe No"};
		
		customerTable = new JTable(data,head);
		customerTableSP = new JScrollPane(customerTable);
		customerTableSP.setBounds(350, 100, 400, 150);
		customerTable.setEnabled(false);
		panel.add(customerTableSP);
		
		customerIdLabel = new JLabel("ID :");
		customerIdLabel.setBounds(100,100,100,30);
		panel.add(customerIdLabel);
		
		if(user.getStatus()==2)
		{
			customerIdTF = new JTextField(user.getUserId());
			customerIdTF.setEnabled(false);
		}
		else
		{
			customerIdTF = new JTextField();
		}
		customerIdTF.setBounds(220,100,100,30);
		panel.add(customerIdTF);
		
		customerNameLabel = new JLabel("Name :");
		customerNameLabel.setBounds(100,150,100,30);
		panel.add(customerNameLabel);
		
		customerNameTF = new JTextField();
		customerNameTF.setBounds(220,150,100,30);
		panel.add(customerNameTF);
		
		addressLabel = new JLabel("Address: ");
		addressLabel.setBounds(100,200,100,30);
		panel.add(addressLabel);
		
		addressTF = new JTextField();
		addressTF.setBounds(220,200,100,30);
		panel.add(addressTF);
		
		phoneNoLabel = new JLabel("Phone no: ");
		phoneNoLabel.setBounds(100,250,100,30);
		panel.add(phoneNoLabel);
		
		phoneNoTF = new JTextField();
		phoneNoTF.setBounds(220,250,100,30);
		panel.add(phoneNoTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(200,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(300,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,300,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 300, 80, 30);
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
			if(!customerIdTF.getText().equals("") || !customerIdTF.getText().equals(null))
			{
				Customer c = cr.searchCustomer(customerIdTF.getText());
				if(c!= null)
				{
					customerNameTF.setText(c.getCustomerName());
					addressTF.setText(c.getAddress());
					phoneNoTF.setText(c.getPhoneNo());
					
					customerIdTF.setEnabled(false);
					customerNameTF.setEnabled(true);
					addressTF.setEnabled(true);
					phoneNoTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		
		else if(command.equals(refreshBtn.getText()))
		{
			customerIdTF.setText("");
			customerNameTF.setText("");
			addressTF.setText("");
			phoneNoTF.setText("");
			
			customerIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Customer c = new Customer();
			
			c.setCustomerId(customerIdTF.getText());
			c.setCustomerName(customerNameTF.getText());
			c.setAddress(addressTF.getText());
			c.setPhoneNo(phoneNoTF.getText());
			
			cr.updateInDB(c);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			customerIdTF.setText("");
			customerNameTF.setText("");
			addressTF.setText("");
			phoneNoTF.setText("");
			
			loadBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			customerIdTF.setEnabled(true);
			customerNameTF.setEnabled(true);
			addressTF.setEnabled(true);
			phoneNoTF.setEnabled(true);
		}
		
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = cr.getAllCustomer();
			String head[] = {"Id", "Name", "Address", "Phone No"};
			
			panel.remove(customerTableSP);
			
			customerTable = new JTable(data,head);
			customerTable.setEnabled(false);
			customerTableSP = new JScrollPane(customerTable);
			customerTableSP.setBounds(350, 100, 400, 150);
			panel.add(customerTableSP);
			
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