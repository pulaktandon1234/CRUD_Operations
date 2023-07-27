import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Search
{
Frame f;
TextField t;
JScrollPane p;
JTable table;

void fill()
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/xii_2023?user=root&password=root");
String query="select * from emp";
PreparedStatement st=cn.prepareStatement(query);
ResultSet result=st.executeQuery();
DefaultTableModel tm=new DefaultTableModel();
tm.addColumn("empno");
tm.addColumn("ename");
tm.addColumn("job");
tm.addColumn("mgr");
tm.addColumn("hiredate");
tm.addColumn("sal");
tm.addColumn("comm");
tm.addColumn("Deptno");
while(result.next())
{
String row[]={result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),};
tm.addRow(row);
}
table.setModel(tm);
cn.close();
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}
void fill(String s)
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/xii_2023?user=root&password=root");
String query="select * from emp where ename like '%"+s+"%'  ";
PreparedStatement st=cn.prepareStatement(query);
ResultSet result=st.executeQuery();
DefaultTableModel tm=new DefaultTableModel();
tm.addColumn("empno");
tm.addColumn("ename");
tm.addColumn("job");
tm.addColumn("mgr");
tm.addColumn("hiredate");
tm.addColumn("sal");
tm.addColumn("comm");
tm.addColumn("Deptno");
while(result.next())
{
String row[]={result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),};
tm.addRow(row);
}
table.setModel(tm);
cn.close();
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}


Search()
{
f=new Frame();
f.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
f.dispose();
}
});
t=new TextField();
t.addKeyListener(new KeyAdapter()
{
public void keyReleased(KeyEvent e)
{
fill(t.getText());
}
});
table=new JTable();
p=new JScrollPane(table);
f.add(t,BorderLayout.NORTH);
f.add(p);
f.setVisible(true);
f.setSize(400,400);
fill();
}
public static void main(String ar[])
{
Search s=new Search();
}

}
