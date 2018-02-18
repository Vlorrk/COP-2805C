
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class Assignment6 extends JApplet { //Using Applet instead of JAVAFX for GUI, more comfortable

	
JTextField jTextFieldID = new JTextField(9);  //creating the entry text Fields

JTextField jTextFieldLName = new JTextField(15);

JTextField jTextFieldFName = new JTextField(15);

JTextField jTextFieldMI = new JTextField(1);

JTextField jTextFieldAddress = new JTextField(20);

JTextField jTextFieldCity = new JTextField(20);

JTextField jTextFieldState = new JTextField(2);

JTextField jTextFieldTelephone = new JTextField(10);

JTextField jTextFieldEmail = new JTextField(40);
 
 private Connection connection; //data member for creating connection to the MySQL database

 public void init() {  //method that starts the applet

	 
  connectToDatabase(); //calling method that connects to the DB



  setLayout(new BorderLayout());
  

  JPanel assignmentPanel = new JPanel(new FlowLayout()); //creating Panel where text fields will be
  
  

  //each of these will put a label in front of the text fields where the info is entered.
  	assignmentPanel.add(new JLabel("ID"));
  	assignmentPanel.add(jTextFieldID);
  
  	assignmentPanel.add(new JLabel("Last Name"));
  	assignmentPanel.add(jTextFieldLName);
  
  	assignmentPanel.add(new JLabel("First Name"));
  	assignmentPanel.add(jTextFieldFName);
  
  	assignmentPanel.add(new JLabel("MI"));
  	assignmentPanel.add(jTextFieldMI);
  
  	assignmentPanel.add(new JLabel("Address"));
  	assignmentPanel.add(jTextFieldAddress);
  
  	assignmentPanel.add(new JLabel("City"));
  	assignmentPanel.add(jTextFieldCity);
  
  	assignmentPanel.add(new JLabel("State"));
  	assignmentPanel.add(jTextFieldState);
  
  	assignmentPanel.add(new JLabel("Telephone"));
  	assignmentPanel.add(jTextFieldTelephone);
  
  	assignmentPanel.add(new JLabel("E-mail"));
  	assignmentPanel.add(jTextFieldEmail);
  
  	add(assignmentPanel, BorderLayout.CENTER);
  
  JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
  
  buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
  
  JButton viewButton = new JButton("View");
  buttonPanel.add(viewButton);
  
  JButton insertButton = new JButton("Insert");
  buttonPanel.add(insertButton);
  
  JButton updateButton = new JButton("Update");
  buttonPanel.add(updateButton);
  
  JButton clearButton = new JButton("Clear");
  buttonPanel.add(clearButton);
  
  add(buttonPanel, BorderLayout.SOUTH);
  
  viewButton.addActionListener(new ActionListener() {   //event that happens when hitting the view button
   @Override
   
   public void actionPerformed(ActionEvent idEntered) {
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
      jTextFieldTelephone.setText(rset.getString(7));
      jTextFieldEmail.setText(rset.getString(8));
     } else {
      jTextFieldLName.setText("");
      jTextFieldFName.setText("");
      jTextFieldMI.setText("");
      jTextFieldAddress.setText("");
      jTextFieldCity.setText("");
      jTextFieldState.setText("");
      jTextFieldTelephone.setText("");
      jTextFieldEmail.setText("");
     }
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  insertButton.addActionListener(new ActionListener() {   
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
     preparedStatement.setString(8, jTextFieldTelephone.getText());
     preparedStatement.setString(9, jTextFieldEmail.getText());
     preparedStatement.executeUpdate();
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  updateButton.addActionListener(new ActionListener() {   
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
     preparedStatement.setString(7, jTextFieldTelephone.getText());
     preparedStatement.setString(8, jTextFieldEmail.getText());
     preparedStatement.setString(9, jTextFieldID.getText());
     preparedStatement.executeUpdate();
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
  
  clearButton.addActionListener(new ActionListener() {   
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
     jTextFieldTelephone.setText("");
     jTextFieldEmail.setText("");
    } catch (SQLException e2) {
     e2.printStackTrace();
    }
   }
  });
 }

 private void connectToDatabase() {
  try {
   Class.forName("com.mysql.jdbc.Driver");
   
   //Connecting to my MySQL database named "assignment 6, user and pass listed afterwards
   connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment6", "jbaez34", "assignment6");
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }


}