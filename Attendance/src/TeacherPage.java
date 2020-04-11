
import java.sql.*;
import javax.swing.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherPage extends JFrame{
	private static final long serialVersionUID = 1L;
	JComboBox<String> sec, sub, sem;
	JButton conB;
	String TeacherName;
	Connection con;
    Statement st;
    ResultSet res;
    
	public TeacherPage(String teacher) throws SQLException, ClassNotFoundException {
		super("Teacher Panel");
		
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
		Class.forName("com.mysql.jdbc.Driver");
		st = con.createStatement();
		
		TeacherName = teacher;
		
		sec = new JComboBox<String>();
		sub = new JComboBox<String>();
		sem = new JComboBox<String>();
		conB = new JButton("Confirm");
		conB.setEnabled(false);
		
		
		res = st.executeQuery("select distinct(sem) from teacher where user='"+teacher+"';");
		while(res.next())
			sem.addItem(res.getString(1));
		res.close();

		sem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					res = st.executeQuery("select distinct(sec) from teacher where user='"+teacher+"' and sem = "+sem.getSelectedItem()+";");
					sec.removeAllItems();
					while (res.next())
						sec.addItem(res.getString(1));
					res.close();
					sub.removeAll();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					res = st.executeQuery("select sub from teacher where user='"+teacher+"' and sem = "+sem.getSelectedItem()+" and sec = '"+sec.getSelectedItem()+"';");
					sub.removeAllItems();
					while(res.next())
						sub.addItem(res.getString(1));
					res.close();
					conB.setEnabled(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		

		add(new JLabel("Semester"));
		add(sem);
		add(new JLabel("Section"));
		add(sec);
		add(new JLabel("Subject"));
		add(sub);
		add(conB);
		conB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.out.println("Show the students list");
				try {
					TakeAttendance tk = new TakeAttendance(sec.getSelectedItem()+"", sem.getSelectedItem()+"", sub.getSelectedItem()+"");
			        tk.setVisible(true);
			        tk.setSize(400, 360);
			        tk.setLayout(new GridLayout());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

			}
		});
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		TeacherPage tp = new TeacherPage("T1");
		tp.setVisible(true);
		tp.setSize(320, 180);
		tp.setLayout(new GridLayout(4,2));
	}
}
