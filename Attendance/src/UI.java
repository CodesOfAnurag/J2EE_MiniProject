import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class UI {
	private static Component add;
	
	Teacher cTemp;
	static HashMap<String, String> hm;
	public static void main(String args[]){
	
		// login users
		hm =new HashMap<String, String>();
		hm.put("123", "123");
		hm.put("345", "345");

		hm.put("USN", "DOB");
		
        JFrame user = new JFrame("USER");
        JButton tcB = new JButton("Teacher");
        JButton stB = new JButton("Student");
        
        JLabel utxt = new JLabel("Choose a user");
        user.add(utxt);
        user.add(tcB);
        user.add(stB);
        user.setVisible(true);
		user.setSize(320, 100);
        user.setLayout(new FlowLayout());
		// int i;
        tcB.addActionListener(new ActionListener(){
			int i;
            public void actionPerformed(ActionEvent evt){
				System.out.println("Pressed");
				// JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
				String login = JOptionPane.showInputDialog(null, "Enter Teacher ID");
				if (login==null)
				System.exit(0);
				for(i=0; !hm.keySet().contains(login) && i<2 ;i++) {
					login = JOptionPane.showInputDialog(null, "Enter correct teacher ID", "", JOptionPane.WARNING_MESSAGE);
					if (login==null)
					System.exit(0);
				}
		if (i==3){
			JOptionPane.showMessageDialog(null, "Exceeded maximum attempts","Bye", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		
		String pass = JOptionPane.showInputDialog(null, "Enter Password");
		if (pass==null)
		System.exit(0);
		for(i=0; !hm.get(login).equals(pass) && i<2;i++) {
			pass = JOptionPane.showInputDialog(null, "Enter correct Password", "", JOptionPane.WARNING_MESSAGE);
			if (pass==null)
			System.exit(0);
		}
		
		if (i==3){
			JOptionPane.showMessageDialog(null, "Exceeded maximum attempts","Bye", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		JOptionPane.showMessageDialog(null, "Successful Login", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		HashMap<Integer, Teacher> tlist = new HashMap<Integer, Teacher>();
		String sub[] = {"English", "Physics", "Maths"}; 
		tlist.put(123,new Teacher(123, "Teacher X", sub));
		String sub2[] = {"English", "Computer", "Chemistry"}; 
		
		tlist.put(345, new Teacher(345, "Teacher Y", sub2));

		JFrame tPanel = new JFrame("Teacher's Page");
		Teacher curr= tlist.get(Integer.parseInt(login));
		String currsub[] = curr.subjects;
		for(int i=0;i<currsub.length;i++){
			JButton jb = new JButton(currsub[i]);
			tPanel.add(jb);
		}
		tPanel.setLayout(new FlowLayout());
		tPanel.setVisible(true);
		// tPanel.set
		tPanel.setSize(320, 100);

	}

});
stB.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent evt){
				System.out.println("Student");
			int i;

				// JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
            String login = JOptionPane.showInputDialog(null, "Enter USN");
		if (login==null)
			System.exit(0);
		for(i=0; !hm.keySet().contains(login) && i<2 ;i++) {
			login = JOptionPane.showInputDialog(null, "Enter correct USN", "", JOptionPane.WARNING_MESSAGE);
			if (login==null)
				System.exit(0);
		}
		if (i==3){
			JOptionPane.showMessageDialog(null, "Exceeded maximum attempts","Bye", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
			
		
		String pass = JOptionPane.showInputDialog(null, "Enter Password"); //password can be D.O.B.
		if (pass==null)
			System.exit(0);
		for(i=0; !hm.get(login).equals(pass) && i<2;i++) {
			pass = JOptionPane.showInputDialog(null, "Enter correct Password", "", JOptionPane.WARNING_MESSAGE);
			if (pass==null)
				System.exit(0);
		}
			
		if (i==3){
			JOptionPane.showMessageDialog(null, "Exceeded maximum attempts","Bye", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		JOptionPane.showMessageDialog(null, "Successful Login", "Success", JOptionPane.INFORMATION_MESSAGE);

			}
		});
	 
	}
}