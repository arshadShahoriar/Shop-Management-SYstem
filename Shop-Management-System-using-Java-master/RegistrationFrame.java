package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;

public class RegistrationFrame extends JFrame implements ActionListener
{
	private JButton submitBtn, backBtn;
	private JLabel nameLabel,addressLabel,phoneNoLabel;
	private JTextField nameTf,addressTf,phoneNoTf;
	
	private JPanel panel;
	private LoginFrame lf;
	private UserRepo ur;
	private CustomerRepo cr;
	
	public RegistrationFrame(LoginFrame lf)
	{
		super("Register Now !!!");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.lf = lf;
		
		ur = new UserRepo();
		cr = new CustomerRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(300,100,50,30);
		panel.add(nameLabel);
		
		nameTf = new JTextField();
		nameTf.setBounds(370,100,100,30);
		panel.add(nameTf);
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(300,150,70,30);
		panel.add(addressLabel);
		
		addressTf = new JTextField();
		addressTf.setBounds(370,150,100,30);
		panel.add(addressTf);
		
		phoneNoLabel = new JLabel("Phone no:");
		phoneNoLabel.setBounds(300,200,70,30);
		panel.add(phoneNoLabel);
		
		phoneNoTf = new JTextField();
		phoneNoTf.setBounds(370,200,100,30);
		panel.add(phoneNoTf);
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(300, 300, 80, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);
		
		
		backBtn = new JButton("Back");
		backBtn.setBounds(390, 300, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(submitBtn.getText()))
		{
			Customer c = new Customer();
			User u = new User();
			Random r1 = new Random();
			int id = r1.nextInt(99999);
			int pass = r1.nextInt(9999999)+10000000;
			
			c.setCustomerId("c"+id);
			c.setCustomerName(nameTf.getText());
			c.setAddress(addressTf.getText());
			c.setPhoneNo(phoneNoTf.getText());
			
			u.setUserId("c"+id);
			u.setPassword(pass+"");
			u.setStatus(2);
			
			cr.insertInDB(c);
			ur.insertUser(u);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+"c"+id+"and Password: "+pass);
			
			nameTf.setText("");
			addressTf.setText("");
			phoneNoTf.setText("");
		}
		else if(command.equals(backBtn.getText()))
		{
			this.setVisible(false);
			lf.setVisible(true);
		}
		else{}
	}
}