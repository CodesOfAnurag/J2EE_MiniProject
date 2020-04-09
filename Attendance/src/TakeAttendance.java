
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class TakeAttendance extends JFrame{
	private static final long serialVersionUID = 39647273878804594L;
	static HashMap<String, Integer> attendance;
    JPanel pane, dpane;
    JScrollPane jsp;
    JButton cancel, done;
    Connection con;
    Statement st;
    ResultSet res;
    Integer size;
    TakeAttendance() throws ClassNotFoundException, SQLException{
        super("Attendance");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        attendance = new HashMap<String, Integer>();
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
		Class.forName("com.mysql.jdbc.Driver");
		st = con.createStatement();
        
        res = st.executeQuery("select * from students;");
        size=0;
        if (res != null) { res.last();	size = res.getRow(); }
        System.out.println(size);
        res.close();
        
        pane = new JPanel();
        dpane = new JPanel();
        jsp = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        pane.setLayout(new GridLayout(size+3,3));
        pane.add(new JLabel("USN"));
        pane.add(new JLabel("NAME"));
        pane.add(new JLabel("ABSENT"));

        res = st.executeQuery("select * from students order by usn;");
        while (res.next())
        {
        	String usn = res.getString(1);
        	String name = res.getString(2);
        	attendance.put(usn, 1);
        	pane.add(new JLabel(usn));
        	pane.add(new JLabel(name));
        	JToggleButton btn = new JToggleButton("Present");
            btn.addItemListener(new ItemListener() {  
                    public void itemStateChanged(ItemEvent itemEvent) 
                    { 
                        int state = itemEvent.getStateChange(); 
                        if (state == ItemEvent.SELECTED) { 
                            btn.setText("Absent");
                            attendance.replace(usn, 0);
                        } 
                        else {  
                            btn.setText("Present");
                            attendance.replace(usn, 1);
                        } 
                        System.out.println(attendance);                    
                     } 
             });
        	pane.add(btn);
        }
        res.close();
        
        pane.add(new JPanel());
        pane.add(new JPanel());
        pane.add(new JPanel());
        
        pane.add(new JPanel());
        
        cancel = new JButton("Cancel");
        cancel.setEnabled(false);
        pane.add(cancel);
        
        done = new JButton("Done");
        pane.add(done);
        done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Set<String> usns = attendance.keySet();
				try {
					for (String x : usns) {
						if (attendance.get(x)==1) {
							String sql = "update students set attendance = attendance +1 where usn = '"+x+"';";
							
								st.executeUpdate(sql);
							
						}
					}
					dpane.setLayout(new GridLayout(size+1,3));
					dpane.add(new JLabel("USN"));
					dpane.add(new JLabel("NAME"));
					dpane.add(new JLabel("ATTENDANCE"));
					jsp.setViewportView(dpane);
					res = st.executeQuery("select * from students order by usn;");
					while(res.next()) {
						dpane.add(new JLabel(res.getString(1)));
						dpane.add(new JLabel(res.getString(2)));
						dpane.add(new JLabel(res.getString(3)));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
        add(jsp);
    }

    public static void main(String args[])  throws ClassNotFoundException, SQLException {
        TakeAttendance tk = new TakeAttendance();
        tk.setVisible(true);
        tk.setSize(400, 360);
        tk.setLayout(new GridLayout());
    }
}