
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 8859753445286040949L;
	JButton addS, addT, addA, exitB;
	JPanel pane;

	AdminPage(){
		super("Admin Page");
		pane = new JPanel();
		addS = new JButton("Add new student");
		addT = new JButton("Add new teacher");
		addA = new JButton("Add new admin");
		exitB = new JButton("Exit");
		
		pane.add(addS);
		pane.add(addT);
		pane.add(addA);
		pane.add(exitB);
		pane.setLayout(new GridLayout(4,1));
		add(pane);
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(280, 150);
		
		addS.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent evt){
		 		System.out.println("Adding new student");
				AddStudent adsf;
				try {
					setVisible(false);
					adsf = new AddStudent();
					adsf.setVisible(true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		 	}
		 });
		
		addT.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent evt){
				AddTeacher adtf;
				try {
					setVisible(false);
					adtf = new AddTeacher();
					adtf.setVisible(true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		 	}
		 });
		
		addA.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent evt){
				AddAdmin adaf;
				try {
					setVisible(false);
					adaf = new AddAdmin();
					adaf.setVisible(true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 	}
		 });
		
		exitB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.exit(0);
			}
		});
	}
}
