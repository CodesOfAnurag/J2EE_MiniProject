
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class AddAdmin extends JFrame{
	private static final long serialVersionUID = 9162987075688155414L;
	JPanel pane;
	JTextField user, pass;
	JButton addinfo;
	JScrollPane jsp;

	Connection con;
    Statement st;
    ResultSet res;
    
	AddAdmin() throws SQLException, ClassNotFoundException{
		super("Add Admin");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(320,130);
        
        pane = new JPanel();
        pane.setLayout(new GridLayout(3,2));
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
		Class.forName("com.mysql.jdbc.Driver");
		st = con.createStatement();
		ArrayList<String> users = new ArrayList<String>();
        res = st.executeQuery("select user from login where desg='A';");
        while (res.next())
        	users.add(res.getString(1));
        
        user = new JTextField();
        pass = new JTextField();
        
        addinfo = new JButton("Add");
        
        pane.add(new JLabel("Admin Id"));
        pane.add(user);
        
        pane.add(new JLabel("Password"));
        pane.add(pass);
        
                
        pane.add(new JPanel());
        pane.add(addinfo);
        
        addinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String userText = user.getText();
					String passText = pass.getText();
					if (!users.contains(userText) && !userText.isEmpty() && !passText.isEmpty())
					{
						String sql = "insert into login values('"+userText+"', '"+passText+"', 'A');";
						st.executeUpdate(sql);
						users.add(userText);
						JOptionPane.showMessageDialog(null, "Admin Added", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						throw new Exception("Invalid Input");
					}
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Invalid/Incomplete Data", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}});
        jsp = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp);
	}
}
