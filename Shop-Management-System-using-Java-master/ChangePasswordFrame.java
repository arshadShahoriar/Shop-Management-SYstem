package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import repository.*;
import entity.*;

public class ChangePasswordFrame extends JFrame implements ActionListener
{
private JLabel NameLabel,PassLabel;
private JButton back,submit,logOut;
private JPasswordField first,again;
private JPanel panel;
private User user;
private UserRepo ur;

public ChangePasswordFrame(User user)
{
	    super("Change your password");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		ur = new UserRepo();
		
		panel=new JPanel();
		panel.setLayout(null);
		
		
		NameLabel=new JLabel("New Password: ");
		NameLabel.setBounds(120,100,150,30);
		panel.add(NameLabel);
		
		PassLabel=new JLabel("Retype New Password: ");
		PassLabel.setBounds(120,150,150,30);
		panel.add(PassLabel);
		
		first =new JPasswordField();
		first.setBounds(270,100,100,30);
		first.setEchoChar('*');
		panel.add(first);
		
		
		again=new JPasswordField();
		again.setBounds(270,150,100,30);
		again.setEchoChar('*');
		panel.add(again);
		
		submit=new JButton("Submit");
		submit.setBounds(150,220,80,30);
		submit.addActionListener(this);
		panel.add(submit);
		
		back=new JButton("Back");
		back.setBounds(250,220,80,30);
		back.addActionListener(this);
		panel.add(back);
		
		logOut=new JButton("logOut");
		logOut.setBounds(350,220,80,30);
		logOut.addActionListener(this);
		panel.add(logOut);
		
		
		this.add(panel);
}
public void actionPerformed(ActionEvent ae)
{
	String command=ae.getActionCommand();
	
	if(command.equals(submit.getText()))
	{
		if(first.getText().equals(again.getText()))
		{
			user.setPassword(first.getText());
			ur.updateUser(user);
			JOptionPane.showMessageDialog(this, "UpdATED");
			first.setText("");
			again.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(this, "wrong Password entered in 2nd time");
			first.setText("");
			again.setText("");
		}
	}
	else if(command.equals(logOut.getText()))
	{
		LoginFrame lf = new LoginFrame();
		lf.setVisible(true);
		this.setVisible(false);
	}
	else if(command.equals(back.getText()))
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
	else{}
}
}


