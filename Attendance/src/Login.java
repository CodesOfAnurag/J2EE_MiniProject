
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	private static final long serialVersionUID = 9162987075688155414L;
	JButton student, teacher, admin;
	String LoginTeacher, LoginAdmin;
	Connection con;
    Statement st;
    ResultSet res;
    
	Login() throws SQLException, ClassNotFoundException{
		super("Login");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        super.setLayout(new FlowLayout());
        super.setVisible(true);
        super.setSize(320, 100);
        JPanel pane = new JPanel();
        
        LoginTeacher = null;
        LoginAdmin = null;
        student = new JButton("Student");
        teacher = new JButton("Teacher");
        admin = new JButton("Admin");
        
        pane.add(admin);
        pane.add(teacher);
        pane.add(student);
        add(pane);
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
		Class.forName("com.mysql.jdbc.Driver");
		st = con.createStatement();
		HashMap<String, String> teachers = new HashMap<String, String>();
		HashMap<String, String> admins = new HashMap<String, String>();
		ArrayList<String> students = new ArrayList<String>();
        res = st.executeQuery("select * from login;");
        while (res.next()) {
        	if (res.getString(3).contentEquals("T"))
        		teachers.put(res.getString(1), res.getString(2));
        	else
        		admins.put(res.getString(1), res.getString(2));
        }
        res.close();
        
        res = st.executeQuery("select * from students");
        while(res.next())
        	students.add(res.getString(1));
        res.close();
        System.out.println(teachers);
        System.out.println(admins);
        System.out.println(students);
        
        teacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginTeacher = GenralLoginDialog(teachers);
				System.out.println(LoginTeacher);
				setVisible(false);
				try {
					TeacherPage tp = new TeacherPage(LoginTeacher);
					tp.setVisible(true);
					tp.setSize(320, 180);
					tp.setLayout(new GridLayout(4,2));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginAdmin = GenralLoginDialog(admins);
				System.out.println(LoginAdmin);
				setVisible(false);
				AdminPage ap = new AdminPage();
				ap.setVisible(true);
				ap.setSize(320, 180);
				ap.setLayout(new FlowLayout());
			}
		});
        
        student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usn = JOptionPane.showInputDialog("Enter USN").toUpperCase();
				if (students.contains(usn)) {
					try {
						res = st.executeQuery("select * from students where usn='"+usn+"';");
						setVisible(false);
						JFrame studentInfo = new JFrame("Student Info");
						studentInfo.setLayout(new GridLayout(9,2));
						studentInfo.setVisible(true);
						studentInfo.setSize(240,320);
						String [] labels = {"USN", "Name", "Semester", "Section", "Subject1", "Subject2", "Subject3", "Subject4", "Subject5"};
						while (res.next()) {
							for (int i=0 ; i<9; i++) {
								studentInfo.add(new JLabel(labels[i]));
								studentInfo.add(new JLabel(res.getString(i+1)));
							}
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Invalid USN", "Error", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}				
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid USN", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
	
	public String GenralLoginDialog(HashMap<String, String> hm){
		System.out.println("Pressed");
		int i=0;
		String login = JOptionPane.showInputDialog(null, "Enter ID");
		if (login==null)
			System.exit(0);
		for(i=0; !hm.keySet().contains(login) && i<2 ;i++) {
			login = JOptionPane.showInputDialog(null, "Enter correct ID", "", JOptionPane.WARNING_MESSAGE);
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
		return(login);
	}
}
