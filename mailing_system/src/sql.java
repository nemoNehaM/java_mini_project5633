import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sql {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test1"; 
        String user = "root"; 
        String password = "12345"; 
        
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            
            // Create the Users table
            String createUsersTable = "CREATE TABLE Users ("
                    + "User_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "email_address VARCHAR(100) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "phone_no VARCHAR(20),"
                    + "datestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            Statement usersStatement = connection.createStatement();
            usersStatement.executeUpdate(createUsersTable);
            System.out.println("Users table created successfully!");
            
            // Create the Inbox table
            String createInboxTableQuery = "CREATE TABLE Inbox ("
                                    + "sender_id INT NOT NULL,"
                                    + "receiver_id INT NOT NULL,"
                                    + "subject VARCHAR(255) NOT NULL,"
                                    + "message TEXT NOT NULL,"
                                    + "date_received DATETIME NOT NULL,"
                                    + "FOREIGN KEY (sender_id) REFERENCES Users(user_id),"
                                    + "FOREIGN KEY (receiver_id) REFERENCES Users(user_id)"
                                    + ")";
            Statement msgSendStatement = connection.createStatement();
            msgSendStatement.executeUpdate(createInboxTableQuery);
            System.out.println("Msg_send table created successfully!");
            
            // Create the Admin table
            String createAdminTableQuery = "CREATE TABLE Admin ("
                                    + "admin_id INT PRIMARY KEY AUTO_INCREMENT,"
                                    + "admin_name VARCHAR(255) NOT NULL,"
                                    + "admin_mail_address TEXT NOT NULL,"
                                    + "admin_password VARCHAR(30) NOT NULL"
                                    + ")";
            Statement AdminStatement = connection.createStatement();
            AdminStatement.executeUpdate(createAdminTableQuery);
            System.out.println("Admin table created successfully!");
            
            
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}