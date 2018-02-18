import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Assignment6 extends JApplet {

	
JTextField jTextFieldID = new JTextField(9);
JTextField jTextFieldLName = new JTextField(15);
JTextField jTextFieldFName = new JTextField(15);
JTextField jTextFieldMI = new JTextField(1);
JTextField jTextFieldAddress = new JTextField(20);
JTextField jTextFieldCity = new JTextField(20);
JTextField jTextFieldState = new JTextField(2);
JTextField jTextField8 = new JTextField(10);
JTextField jTextField9 = new JTextField(40);
 
 private Connection connection;

 public void init() {
  setLayout(new BorderLayout());
  connectToDatabase();

  JPanel jPanel1 = new JPanel(new FlowLayout());
  jPanel1.setBorder(new TitledBorder("Staff information"));
  jPanel1.add(new JLabel("ID"));
  jPanel1.add(jTextFieldID);
  jPanel1.add(new JLabel("Last Name"));
  jPanel1.add(jTextFieldLName);
  jPanel1.add(new JLabel("First Name"));
  jPanel1.add(jTextFieldFName);
  jPanel1.add(new JLabel("MI"));
  jPanel1.add(jTextFieldMI);
  jPanel1.add(new JLabel("Address"));
  jPanel1.add(jTextFieldAddress);
  jPanel1.add(new JLabel("City"));
  jPanel1.add(jTextFieldCity);
  jPanel1.add(new JLabel("State"));
  jPanel1.add(jTextFieldState);
  jPanel1.add(new JLabel("Telephone"));
  jPanel1.add(jTextField8);
  jPanel1.add(new JLabel("E-mail"));
  jPanel1.add(jTextField9);
  add(jPanel1, BorderLayout.CENTER);
  
  JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
  jPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
  JButton jButton1 = new JButton("View");
  jPanel2.add(jButton1);
  JButton jButton2 = new JButton("Insert");
  jPanel2.add(jButton2);
  JButton jButton3 = new JButton("Update");
  jPanel2.add(jButton3);
  JButton jButton4 = new JButton("Clear");
  jPanel2.add(jButton4);
  add(jPanel2, BorderLayout.SOUTH);
  
  jButton1.addActionListener(new ActionListener() {   
   @Override
   public void actionPerformed(ActionEvent aerg0) {
    String queryString = "select lastName, firstName, mi, address, city, state, telephone, email from Staff where id = ?;";
    try {
     PreparedStatement preparedStatement = connection.prepareStatement(queryString);
     preparedStatement.setString(1, jTextFieldID.getText());
     ResultSet rset = preparedStatement.executeQuery();
     if (rset.next()) {
      jTextFieldLName.setText(rset.getString(1));
      jTextFieldFName.setText(rset.getString(2));
      jTextFieldMI.setText(rset.getString(3));
      jTextFieldAddress.setText(rset.getString(4));
      jTextFieldCity.setText(rset.getString(5));
      jTextFieldState.setText(rset.getString(6));
      jTextField8.setText(rset.getString(7));
      jTextField9.setText(rset.getString(8));
     } else {
      jTextFieldLName.setText("");
      jTextFieldFName.setText("");
      jTextFieldMI.setText("");
      jTextFieldAddress.setText("");
      jTextFieldCity.setText("");
      jTextFieldState.setText("");
      jTextField8.setText("");
      jTextField9.setText("");
     }
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  jButton2.addActionListener(new ActionListener() {   
   @Override
   public void actionPerformed(ActionEvent e) {
    String queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    try {
     PreparedStatement preparedStatement = connection.prepareStatement(queryString);
     preparedStatement.setString(1, jTextFieldID.getText());
     preparedStatement.setString(2, jTextFieldLName.getText());
     preparedStatement.setString(3, jTextFieldFName.getText());
     preparedStatement.setString(4, jTextFieldMI.getText());
     preparedStatement.setString(5, jTextFieldAddress.getText());
     preparedStatement.setString(6, jTextFieldCity.getText());
     preparedStatement.setString(7, jTextFieldState.getText());
     preparedStatement.setString(8, jTextField8.getText());
     preparedStatement.setString(9, jTextField9.getText());
     preparedStatement.executeUpdate();
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  jButton3.addActionListener(new ActionListener() {   
   @Override
   public void actionPerformed(ActionEvent e) {
    String queryString = "update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? where id = ?";
    try {
     PreparedStatement preparedStatement = connection.prepareStatement(queryString);
     preparedStatement.setString(1, jTextFieldLName.getText());
     preparedStatement.setString(2, jTextFieldFName.getText());
     preparedStatement.setString(3, jTextFieldMI.getText());
     preparedStatement.setString(4, jTextFieldAddress.getText());
     preparedStatement.setString(5, jTextFieldCity.getText());
     preparedStatement.setString(6, jTextFieldState.getText());
     preparedStatement.setString(7, jTextField8.getText());
     preparedStatement.setString(8, jTextField9.getText());
     preparedStatement.setString(9, jTextFieldID.getText());
     preparedStatement.executeUpdate();
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  jButton4.addActionListener(new ActionListener() {   
   @Override
   public void actionPerformed(ActionEvent e) {
    try {
     Statement statement = connection.createStatement();
     statement.executeUpdate("delete from Staff;");
     jTextFieldID.setText("");
     jTextFieldLName.setText("");
     jTextFieldFName.setText("");
     jTextFieldMI.setText("");
     jTextFieldAddress.setText("");
     jTextFieldCity.setText("");
     jTextFieldState.setText("");
     jTextField8.setText("");
     jTextField9.setText("");
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
 }

 private void connectToDatabase() {
  try {
   Class.forName("com.mysql.jdbc.Driver");
   connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment6", "root", "root");
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }

 /** Main method */
 public static void main(String[] args) {
  Assignment6 applet = new Assignment6();
  JFrame frame = new JFrame();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setTitle("Exercise01");
  frame.getContentPane().add(applet, BorderLayout.CENTER);
  applet.init();
  applet.start();
  frame.setSize(540, 200);
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
 }
}