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
		hm.put("admin","admin");

        JFrame user = new JFrame("USER");
        JButton tcB = new JButton("Teacher");
        JButton stB = new JButton("Student");
		JButton adB = new JButton("Admin");
		
        JLabel utxt = new JLabel("Choose a user");
        user.add(utxt);
        user.add(tcB);
        user.add(stB);
		user.add(adB);

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
		JFrame topt = new JFrame("Teacher Panel");

		String sec[] = {"A","B","C"};
		JComboBox secB = new JComboBox<>(sec);

		String subT[] = {"Physics","Chemistry","Maths"};
		JComboBox subB = new JComboBox<>(subT);

		String semT[] = {"1", "2" ,"3"};
		JComboBox semB = new JComboBox<>(semT);
		JButton conB = new JButton("Confirm");

		topt.setVisible(true);
		topt.setSize(420, 100);
		topt.setLayout(new FlowLayout());
		topt.add(secB);
		topt.add(subB);
		topt.add(semB);
		topt.add(conB);

		conB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.out.println("Show the students list");
				// HashMap<Integer, Teacher> tlist = new HashMap<Integer, Teacher>();
				// String sub[] = {"English", "Physics", "Maths"}; 
				// tlist.put(123,new Teacher(123, "Teacher X", new Semester(4,sub)));
				// String sub2[] = {"English", "Computer", "Chemistry"}; 
				
				// tlist.put(345, new Teacher(345, "Teacher Y", new Semester(6,sub2)));
		
				// JFrame tPanel = new JFrame("Teacher's Page");
				// Teacher curr= tlist.get(Integer.parseInt(login));
				// String currsub[] = curr.sem.sub;
				// for(int i=0;i<currsub.length;i++){
				// 	JButton jb = new JButton(currsub[i]);
				// 	tPanel.add(jb);
				// }
				// tPanel.setLayout(new FlowLayout());
				// tPanel.setVisible(true);
				// // tPanel.set
				// tPanel.setSize(320, 100);

			}
		});

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
	 
		adB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.out.println("Admin");
				int i;
				String login = JOptionPane.showInputDialog(null, "Enter login id");
				if(login == null)
					System.exit(0);
				
					for(i=0; !hm.keySet().contains(login) && i<2 ;i++) {
						login = JOptionPane.showInputDialog(null, "Enter correct login", "", JOptionPane.WARNING_MESSAGE);
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
			
			
				JFrame adminF = new JFrame("Welcome to admin panel");
				JButton addS = new JButton("Add new student");
				JButton addT = new JButton("Add new Teacher");

				adminF.add(addS);
				adminF.add(addT);
				adminF.setVisible(true);
				adminF.setLayout(new FlowLayout());
				adminF.setSize(320, 100);

				// addS.addActionListener(new ActionListener(){
				// 	public void actionPerformed(ActionEvent evt){
				// 		System.out.println("Adding new student");
				// 		JFrame addStdF = new JFrame("Add new student");
				// 		addStdF.setSize(320, 320);
				// 	}
				// });

				// addT.addActionListener(new ActionListener(){
				// 	public void actionPerformed(ActionEvent evt){
				// 		System.out.println("Adding new teacher");
				// 		JFrame addTchF = new JFrame("Add new teacher");

				// 		JLabel tname = new JLabel("Teacher's Name:");
				// 		// JTextField t
				// 		addTchF.setVisible(true);
				// 		addTchF.setSize(320, 320);
				// 		addTchF.setLayout(new FlowLayout());
				// 	}
				// })
			}
		});
	}
}
