
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;




public class studentmanagementsystem extends JFrame implements ActionListener {
    private final JLabel studentIdLabel, firstNameLabel, lastNameLabel, courseLabel, phoneLabel, cgpaLabel, dobLabel;
    private final JTextField studentIdField, firstNameField, lastNameField, courseField, phoneField, cgpaField, dobField;
    private final JButton addButton, displayButton, sortButton, searchButton, modifyButton,exitButton,deleteButton;
   
    private Statement stmt;
    

    public studentmanagementsystem() {
    	
    
    	
    	
    		
   	JFrame frame = new JFrame(" Welcome in Student Database");
    JInternalFrame in = new JInternalFrame();
    in.setTitle("Student Management System");
    in.setLayout(new GridLayout(0, 2, 10,5)); 
    
  	


        studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setFont(new Font("Courier", Font.PLAIN, 18));
        
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        phoneLabel = new JLabel("Phone:");
        phoneLabel .setFont(new Font("Arial", Font.PLAIN, 18));
        
        cgpaLabel = new JLabel("CGPA:");
        cgpaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        
        dobLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
       
        studentIdField = new JTextField(15);
        
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        courseField = new JTextField(15);
        phoneField = new JTextField(15);
        cgpaField = new JTextField(15);
        dobField = new JTextField(15);
       
        
       

       
     
        exitButton = new JButton("Exit");
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
       
       
        
        addButton = new JButton("Add");
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
        
        
        displayButton = new JButton("Display");
        displayButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
       
        
        sortButton = new JButton("Sort");
        sortButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
        
        searchButton = new JButton("Search");
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        
        modifyButton = new JButton("Modify");
        modifyButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
       
        
        deleteButton = new JButton("Delete");
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
        
        exitButton.addActionListener(this);
        addButton.addActionListener(this);
        displayButton.addActionListener(this);
        sortButton.addActionListener(this);
        searchButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
      
    
        in.add(studentIdLabel);
        in.add(studentIdField);
        in.add(firstNameLabel);
        in.add(firstNameField);
        in.add(lastNameLabel);
        in.add(lastNameField);
        in.add(courseLabel);
        in.add(courseField);
        in.add(phoneLabel);
        in.add(phoneField);
        in.add(cgpaLabel);
        in.add(cgpaField);
        in.add(dobLabel);
        in.add(dobField);
        in.add(addButton);
        in.add(displayButton);
        in.add(sortButton);
        in.add(searchButton);
        in.add(modifyButton);
        in.add(exitButton);
        in.add(deleteButton);
        
        

         
        in.setVisible(true);
       
       


           
        frame.add(in);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
 
}   
    
    
       
   
    private boolean isValidStudentId(String studentId) 
    {
    		return studentId.matches("\\d+");
    }

    private boolean isValidName(String name)
    {
        return name.matches("[a-zA-Z]+")&& name.length() > 1;
    }

    
    private boolean isValidCGPA(String cgpa) 
    {
        try {
            double cgpaValue = Double.parseDouble(cgpa);
            return cgpaValue >= 0 && cgpaValue <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
 }
    

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
    

    private boolean isValidcourse(String course) {
        return course.matches("[a-zA-Z]+");
    }
    
    
    private boolean isValidDOB(String dob) {
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        return dob.matches(datePattern);
    }


    
    public void actionPerformed(ActionEvent e) {
        dbConnect db = new dbConnect();
        Connection conn;
        try {
            conn = db.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Table tb = new Table();
        

        
        if (e.getSource() == addButton) {
            String studentId = studentIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String course = courseField.getText();
            String phone = phoneField.getText();
            String cgpa = cgpaField.getText();
            String dob = dobField.getText();

            if (!isValidStudentId(studentId)) {
                JOptionPane.showMessageDialog(null, "Invalid Student ID. It should contain only numbers.");
            } else if (!isValidName(firstName) || !isValidName(lastName)) {
                JOptionPane.showMessageDialog(null, "Invalid First Name or Last Name.");
            } else if (!isValidcourse(course)) {
                JOptionPane.showMessageDialog(null, "Invalid Major. It should contain only letters.");
            } else if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number. It should contain exactly 10 digits.");
            } else if (!isValidCGPA(cgpa)) {
                JOptionPane.showMessageDialog(null, "Invalid GPA. It should be a decimal number between 0 and 10.");
            } else if (!isValidDOB(dob)) {
                JOptionPane.showMessageDialog(null, "Invalid Date of Birth. It should be in the format yyyy-mm-dd.");
            }else {
                String sql = "INSERT INTO sdata VALUES('" + studentId + "', '" + firstName + "', '" + lastName + "', '"
                        + course + "', '" + phone + "', '" + cgpa + "', '" + dob + "')";
                try {
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Student added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
  }
        
        
        
        
        
        else if (e.getSource() == displayButton) {
        	
        	
            String sql = "SELECT * FROM sdata";

            try 
            {
                ResultSet rs = stmt.executeQuery(sql);

                
                JTable table = new JTable(tb.buildTableModel(rs));
                JScrollPane scrollPane = new JScrollPane(table);
                
                scrollPane.setPreferredSize(new Dimension(800, 400));
                JOptionPane.showMessageDialog(null,scrollPane );
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        } 
        
        
        
        
        else if (e.getSource() == deleteButton) {
        	
            String studentId = JOptionPane.showInputDialog("Enter student ID to delete:");

            String sql = "DELETE FROM sdata WHERE student_id = '" + studentId + "'";

            try {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Student data deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Student with ID " + studentId + " not found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
       
 }
        
       
        else if (e.getSource() == sortButton) {
        	
        	
            String[] options = {"First Name", "Last Name", "course","student id"};
            int choice = JOptionPane.showOptionDialog(null, "Sort by:", "Sort", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            String sql = "";
            switch (choice) {
                case 0 -> sql = "SELECT * FROM sdata ORDER BY first_name";
                case 1 -> sql = "SELECT * FROM sdata ORDER BY last_name";
                case 2 -> sql = "SELECT * FROM sdata ORDER BY course";
                case 3 -> sql = "SELECT * FROM sdata ORDER BY Student_ID";
                		
                default -> {
                }
            }
            try {
                ResultSet rs = stmt.executeQuery(sql);

                
                JTable table = new JTable(tb.buildTableModel(rs));
                JScrollPane scrollPane = new JScrollPane(table);
                
                scrollPane.setPreferredSize(new Dimension(800, 400));
                JOptionPane.showMessageDialog(null,scrollPane );
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
       
} 
        
        
        else if (e.getSource() == exitButton)
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) 
            {
                System.exit(0);
            }
}
        

        
        else if (e.getSource() == searchButton) {
        	
        	
            
        	
            String[] options = {"Student ID", "Last Name", "course"};
            int choice = JOptionPane.showOptionDialog(null, "Search by:", "Search", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            String column = "";
            switch (choice) {
                case 0 -> column = "student_id";
                case 1 -> column = "last_name";
                case 2 -> column = "course";
                default -> {
                }
}

            String searchTerm = JOptionPane.showInputDialog("Enter search term:");

            String sql = "SELECT * FROM sdata WHERE " + column + " LIKE '%" + searchTerm + "%'";

            try 
            {
                ResultSet rs = stmt.executeQuery(sql);

                
                JTable table = new JTable(tb.buildTableModel(rs));
                JScrollPane scrollPane = new JScrollPane(table);
                
                scrollPane.setPreferredSize(new Dimension(800, 400));
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Student with ID " + searchTerm + " not found.");
                } else {
                    JOptionPane.showMessageDialog(null, scrollPane);
                }
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
        
        
        
        else if (e.getSource() == modifyButton) 
        {
        	
            String studentId = JOptionPane.showInputDialog("Enter student ID:");

            String sql = "SELECT * FROM sdata WHERE student_id = '" + studentId + "'";

            try 
            {
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    String[] options = {"First Name", "Last Name", "course", "Phone", "CGPA", "Date of Birth"};
                    int choice = JOptionPane.showOptionDialog(null, "Select field to modify:", "Modify",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    String column = "";
                    switch (choice) {
                        case 0 -> column = "first_name";
                        case 1 -> column = "last_name";
                        case 2 -> column = "course";
                        case 3 -> column = "phone";
                        case 4 -> column = "cgpa";
                        case 5 -> column = "date_of_birth";
                        default -> {
                        }
                    }
                    String newValue = JOptionPane.showInputDialog("Enter new value:");

                    sql = "UPDATE sdata SET " + column + " = '" + newValue + "' WHERE student_id = '" + studentId + "'";

                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Student data updated successfully.");
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "Student not found.");
                }
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
    }

}
