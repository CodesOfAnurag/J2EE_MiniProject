
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class AddStudent extends JFrame{
	private static final long serialVersionUID = 9162987075688155414L;
	JPanel pane;
	JTextField name, usn;
	JButton addinfo, back;
	JScrollPane jsp;

	Connection con;
    Statement st;
    ResultSet res;
    
	AddStudent() throws SQLException, ClassNotFoundException{
		super("Add Student");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        pane = new JPanel();
        pane.setLayout(new GridLayout(3,3));
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
		Class.forName("com.mysql.jdbc.Driver");
		st = con.createStatement();
		ArrayList<String> usns = new ArrayList<String>();
        res = st.executeQuery("select * from students;");
        while (res.next())
        	usns.add(res.getString(1));
        
        name = new JTextField();
        usn = new JTextField();
        
        addinfo = new JButton("Add");
        back = new JButton("Back");
        back.setEnabled(false);
        
        pane.add(new JLabel("USN"));
        pane.add(usn);
        
        pane.add(new JLabel("Name"));
        pane.add(name);
        
        pane.add(back);
        pane.add(addinfo);
        
        addinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String usnText = usn.getText();
					if (!usns.contains(usnText))
					{
						String nameText = name.getText();
						String sql = "insert into students values('"+usnText+"', '"+nameText+"', 0)";
						st.executeUpdate(sql);
						usns.add(usnText);
						JOptionPane.showMessageDialog(null, "Student Added", "Success", JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}});
        jsp = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp);
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		AddStudent adsf = new AddStudent();
		adsf.setVisible(true);
		adsf.setSize(400,150);
	}
}
