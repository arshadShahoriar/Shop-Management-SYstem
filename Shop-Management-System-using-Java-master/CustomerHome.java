package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class CustomerHome extends JFrame implements ActionListener
{
	private JButton searchProductBtn,seePurchaseInfoBtn,changePassBtn,logoutBtn,updateInfoBtn;
	private JPanel panel;
	private User user;
	
	public CustomerHome(User user)
	{
		super("CustomerHome");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		searchProductBtn = new JButton("Search Product");
		searchProductBtn.setBounds(50, 100, 150, 30);
		searchProductBtn.addActionListener(this);
		panel.add(searchProductBtn);
		
		seePurchaseInfoBtn = new JButton("See PurchaseInfo");
		seePurchaseInfoBtn.setBounds(225, 100, 150, 30);
		seePurchaseInfoBtn.addActionListener(this);
		panel.add(seePurchaseInfoBtn);
		
		changePassBtn = new JButton("Change Password");
		changePassBtn.setBounds(400, 100, 150, 30);
		changePassBtn.addActionListener(this);
		panel.add(changePassBtn);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 100, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		updateInfoBtn = new JButton("Update Info");
		updateInfoBtn.setBounds(250,150,150,30);
		updateInfoBtn.addActionListener(this);
		panel.add(updateInfoBtn);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(searchProductBtn.getText()))
		{
			
			ProductFrame pf = new ProductFrame(user);
			pf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(seePurchaseInfoBtn.getText()))
		{
			PurchaseInfoFrame pf = new PurchaseInfoFrame(user);
			pf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(changePassBtn.getText()))
		{
			ChangePasswordFrame cf = new ChangePasswordFrame(user);
			cf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(updateInfoBtn.getText()))
		{
			CustomerFrame cuf = new CustomerFrame(user);
			cuf.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}