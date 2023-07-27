import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class Program
{
Frame f;
TextField t1,t2,t3;
Label l1,l2,l3;
Button b1,b2,b3,b4;
Program()
{
f=new Frame();
f.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
f.dispose();
}
});
l1=new Label("Enter Rno");
l2=new Label("Enter Name");
l3=new Label("Enter Marks");
t1=new TextField();
t2=new TextField();
t3=new TextField();
b1=new Button("Save");
b2=new Button("Delete");
b3=new Button("Update");
b4=new Button("Find");

b4.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/java_jun_23?user=root&password=root");
String query="select * from student where rno=?";
PreparedStatement st=cn.prepareStatement(query);
st.setInt(1,Integer.parseInt(t1.getText()));

ResultSet result=st.executeQuery();
if(result.next())
{
t2.setText(result.getString(2));
t3.setText(result.getString(3));
}
else
{
JOptionPane.showMessageDialog(null,"Invalid Rno");
}
cn.close();

}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
});


b1.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
//1. Load Driver
Class.forName("com.mysql.cj.jdbc.Driver");
//2. Connect with database

Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/java_jun_23?user=root&password=root");

//3. Prepare Query to be executed
String query="insert into student values(?,?,?)";
PreparedStatement st=cn.prepareStatement(query);
st.setInt(1,Integer.parseInt(t1.getText()));
st.setString(2,t2.getText());
st.setFloat(3,Float.parseFloat(t3.getText()));
//4. Execute Query
// insert delete update --- executeUpdate()
// select ---- executeQuery()
st.executeUpdate();
//5. Close Connection
cn.close();
JOptionPane.showMessageDialog(null,"Data has been saved");
t1.setText("");
t2.setText("");
t3.setText("");
}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
});
b2.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
//1. Load Driver
Class.forName("com.mysql.cj.jdbc.Driver");
//2. Connect with database

Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/java_jun_23?user=root&password=root");

//3. Prepare Query to be executed
String query="delete from student where rno=?";
PreparedStatement st=cn.prepareStatement(query);
st.setInt(1,Integer.parseInt(t1.getText()));

//4. Execute Query
// insert delete update --- executeUpdate()
// select ---- executeQuery()
st.executeUpdate();
//5. Close Connection
cn.close();
JOptionPane.showMessageDialog(null,"Data has been deleted");
t1.setText("");
t2.setText("");
t3.setText("");
}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
});
b3.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
//1. Load Driver
Class.forName("com.mysql.cj.jdbc.Driver");
//2. Connect with database

Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/java_jun_23?user=root&password=root");

//3. Prepare Query to be executed
String query="update student set name=?,marks=? where rno=?";
PreparedStatement st=cn.prepareStatement(query);
st.setString(1,t2.getText());
st.setFloat(2,Float.parseFloat(t3.getText()));
st.setInt(3,Integer.parseInt(t1.getText()));
//4. Execute Query
// insert delete update --- executeUpdate()
// select ---- executeQuery()
st.executeUpdate();
//5. Close Connection
cn.close();
JOptionPane.showMessageDialog(null,"Data has been updated");

}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
});
f.setLayout(new GridLayout(10,1));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(t2);
f.add(l3);
f.add(t3);

f.add(b4);

f.add(b1);
f.add(b2);
f.add(b3);
f.setVisible(true);
f.setSize(400,400);
}
public static void main(String ar[])
{
Program p=new Program();
}
}
