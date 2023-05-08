import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class login {
   

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test1"; // Replace "mydatabase" with your database name
        String user = "root"; // Replace "username" with your database username
        String password1 = "nehaniki123@J"; // Replace "password" with your database password
        
        try {
            // Connect to the database
           // Class.forName("com.mysql.cj.jdbc.Driver");
          // Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password1);
            
            // Create the statement object
            Statement statement = connection.createStatement();

            int choice;
            
            // Prompt the user to choose an option
            do{
                System.out.println("\033[1;31m+----------------------------------+");
                System.out.println("\033[1;33m|         Welcome to CMail         |");
                System.out.println("\033[1;31m+----------------------------------+");
                System.out.println("\033[1;31m| Options:                         |");
                System.out.println("\033[1;31m|        1. User Login             |");
                System.out.println("\033[1;31m|        2. Admin Login            |");
                System.out.println("\033[1;31m|        3. New User               |");
                System.out.println("\033[1;31m|        4. Forgot Password        |");
                System.out.println("\033[1;31m|        5. Exit                   |");
                System.out.println("\033[1;31m+----------------------------------+");


                choice = Integer.parseInt(System.console().readLine());
                String BLUE = "\u001B[34m";
                String GREEN = "\u001B[32m";
                String RESET = "\u001B[0m";
                
             
             System.out.println();

                switch (choice) {
                    case 1:
                    System.out.println(BLUE + "+----------------------------------+" + RESET);
                    System.out.println(BLUE + "|           User Login             |" + RESET);
                    System.out.println(BLUE + "+----------------------------------+" + RESET);
                    System.out.println(BLUE + "| Please enter your email address: |" + RESET);
                    System.out.println(BLUE + "+----------------------------------+" + RESET);
                    String email = System.console().readLine();
                    System.out.println();
                    System.out.println(GREEN + "+----------------------------------+" + RESET);
                    System.out.println(GREEN + "| Please enter your password:      |" + RESET);
                    System.out.println(GREEN + "+----------------------------------+" + RESET);
                    String password = new String(System.console().readPassword());
                    System.out.println();
                    String query = "SELECT * FROM Users WHERE email_address = '" + email + "' AND password = '" + password + "'";
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        // Successful login
                        System.out.println("\u001B[34m+----------------------------------+");
                        System.out.println("|           \u001B[32mLogin Success\u001B[34m          |");
                        System.out.println("+----------------------------------+");
                        System.out.println("| Welcome back, \u001B[33m" + resultSet.getString("username") + "\u001B[34m!         |");
                        System.out.println("+----------------------------------+\u001B[0m");

                        // System.out.println("Login successful!");
                        
                        // Code to view inbox and send messages.

                        int options;
                        do{
                            System.out.println("\u001B[36m+----------------------------------+");
                            System.out.println("|         \u001B[35mWelcome to CMail         \u001B[36m|");
                            System.out.println("+----------------------------------+");
                            System.out.println("| Options:                         |");
                            System.out.println("|        \u001B[32m1. View Inbox             \u001B[36m|");
                            System.out.println("|        \u001B[33m2. Send messages          \u001B[36m|");
                            System.out.println("|        \u001B[31m3. Logout (Press -1)      \u001B[36m|");
                            System.out.println("+----------------------------------+\u001B[0m");
                            

                            options = Integer.parseInt(System.console().readLine());
                            System.out.println();
                            
                            switch(options){
                                case 1:
                                    // Define the SQL query for selecting the user ID where the email matches the input email address
                                    String selectUserQuery = "SELECT user_id FROM Users WHERE email_address = '" + email + "'";

                                    // Execute the query to select the user ID
                                    ResultSet resultSet0 = statement.executeQuery(selectUserQuery);
                                    // int userID = resultSet0.getInt("user_id");
                                    int userID;

                                    if (resultSet0.next()) {
                                        userID = resultSet0.getInt("user_id");
                                        // System.out.println("User ID for " + email + ": " + userID);
                                        // if (!resultSet0.isBeforeFirst()) {
                                        //     System.out.println("No messages found in the inbox!");
                                        // }
                                        // else{
                                        String selectInboxQuery = "SELECT * FROM Inbox WHERE receiver_id = " + userID;
                                        // Execute the query to select all messages from the Inbox table
                                        ResultSet resultSet8 = statement.executeQuery(selectInboxQuery);
                                        // Loop through the result set and print out the message details
                                        while (resultSet8.next()) {
                                            int messageID = resultSet8.getInt("receiver_id");
                                            int senderID = resultSet8.getInt("sender_id");
                                            String subject = resultSet8.getString("subject");
                                            String messageBody = resultSet8.getString("message");
                                            Date dateReceived = resultSet8.getDate("date_received");
                                            
                                            // Print out the message details
                                           // ANSI escape sequences for different colors
                                            final String ANSI_RESET = "\u001B[0m";
                                            final String ANSI_RED = "\u001B[31m";
                                            final String ANSI_GREEN = "\u001B[32m";
                                            final String ANSI_BLUE = "\u001B[34m";

                                            System.out.println(ANSI_RED + "Message ID: " + messageID + ANSI_RESET);
                                            System.out.println(ANSI_GREEN + "Sender ID: " + senderID + ANSI_RESET);
                                            System.out.println(ANSI_BLUE + "Subject: " + subject + ANSI_RESET);
                                            System.out.println("Message Body: " + messageBody);
                                            System.out.println("Date Received: " + dateReceived);
                                            System.out.println("----------------------");

                                        }
                                    // }
                                        
                                    } 

                                    break;

                                    case 2:

                                        String selectUserQuery11 = "SELECT user_id FROM Users WHERE email_address = '" + email + "'";

                                        // Execute the query to select the user ID
                                        ResultSet resultSet00 = statement.executeQuery(selectUserQuery11);
                                        int userIDr;

                                        if (resultSet00.next()) {
                                            userIDr = resultSet00.getInt("user_id");
                                        }
                                        userIDr = resultSet00.getInt("user_id");
                                        Scanner scanner = new Scanner(System.in);
                                        // Ask the user for the recipient's email address
                                        final String ANSI_CYAN = "\u001B[36m";
                                        final String ANSI_RESET = "\u001B[0m";
                                        
                                        System.out.print(ANSI_CYAN + "Enter recipient's email address: " + ANSI_RESET);
                                        
                                        String receiverEmail = scanner.nextLine();

                                        // Define the SQL query for selecting the recipient's ID from the User table
                                        String selectUserQueryr = "SELECT user_id FROM Users WHERE email_address = '" + receiverEmail + "'";

                                        // Execute the query to select the recipient's ID from the User table
                                        ResultSet resultSet6 = statement.executeQuery(selectUserQueryr);
                                        int receiverID;

                                        if (resultSet6.next()) {
                                            receiverID = resultSet6.getInt("user_id");
                                        }
                                        receiverID = resultSet6.getInt("user_id");
                                        // Ask the user for the message subject and body
                                        final String ANSI_YELLOW = "\u001B[33m";
                                       // final String ANSI_RESET = "\u001B[0m";
                                        
                                        System.out.print(ANSI_YELLOW + "Enter message subject: " + ANSI_RESET);
                                        String subject = scanner.nextLine();
                                        System.out.print(ANSI_YELLOW + "Enter message body: " + ANSI_RESET);
                                        String messageBody = scanner.nextLine();
                                        
                                        // Define the SQL query for inserting a new message into the Inbox table
                                        String insertInboxQuery = "INSERT INTO Inbox (sender_id, receiver_id, subject, message, date_received) VALUES (" 
                                        + userIDr + "," + receiverID + ",'" + subject + "','" + messageBody + "',NOW())";

                                        // Execute the query to insert the new message into the Inbox table
                                        statement.executeUpdate(insertInboxQuery);

                                        System.out.println("Message sent successfully!");
                                        break;

                                    
                                    
                                    default:
                                        
                                        System.out.print("Invalid choice!!");
                                    
                                        break;
                        

                            }



                        }while(options != -1);


                        } else {
                            // Invalid email or password
                            final String ANSI_BLUE = "\u001B[34m";
                            final String ANSI_RESET = "\u001B[0m";
                            
                            System.out.println(ANSI_BLUE + "+----------------------------------+");
                            System.out.println("|           Login Failed           |");
                            System.out.println("+----------------------------------+");
                            System.out.println("| Invalid email or password.       |");
                            System.out.println("| Please try again.                |");
                            System.out.println("+----------------------------------+" + ANSI_RESET);
                            
                        }
                                            break;
                                case 2:

                                final String ANSI_CYAN = "\u001B[36m";
                                final String ANSI_RESET = "\u001B[0m";
                                
                                System.out.println(ANSI_CYAN + "+----------------------------------+");
                                System.out.println("|           Admin Login            |");
                                System.out.println("+----------------------------------+");
                                System.out.println("| Please enter your email address: |");
                                System.out.println("+----------------------------------+" + ANSI_RESET);
                                
                                String a_email = System.console().readLine();
                                
                                System.out.println();
                                System.out.println(ANSI_CYAN + "+----------------------------------+");
                                System.out.println("| Please enter your password:      |");
                                System.out.println("+----------------------------------+" + ANSI_RESET);
                                
                            String a_password = new String(System.console().readPassword());
                            System.out.println();
                            String query48 = "SELECT * FROM admin WHERE admin_mail_address = '" + a_email + "' AND admin_password = '" + a_password + "'";
                            ResultSet resultSet48 = statement.executeQuery(query48);
                            if (resultSet48.next()) {
                                // Successful login
                                final String ANSI_GREEN = "\u001B[32m";
                                //inal String ANSI_RESET = "\u001B[0m";

                                System.out.println("+----------------------------------+");
                                System.out.println("|           Login Success          |");
                                System.out.println("+----------------------------------+");
                                System.out.println("| Welcome back, " + ANSI_GREEN + resultSet48.getString("admin_name") + ANSI_RESET + "!         |");
                                System.out.println("+----------------------------------+");

                                int a_options;
                                do {
                                    final String ANSI_BLUE = "\u001B[34m";
                                  //  final String ANSI_RESET = "\u001B[0m";
                                    
                                    System.out.println("+----------------------------------+");
                                    System.out.println("|         Welcome to CMail         |");
                                    System.out.println("+----------------------------------+");
                                    System.out.println("| Options:                         |");
                                    System.out.println(ANSI_BLUE + "|        1. See users              |");
                                    System.out.println("|        2. Delete users           |");
                                    System.out.println("|        3. See all messages       |");
                                    System.out.println("|        4. See the statistics     |");
                                    System.out.println("+----------------------------------+");
                                    
                               

                                    a_options = Integer.parseInt(System.console().readLine());
                                    System.out.println();

                                    switch(a_options){
                                        case 1:
                                            // See all users
                                            String selectAdminQuery = "SELECT * FROM users";
                                            ResultSet rs = statement.executeQuery(selectAdminQuery);
                                            while (rs.next()) {
                                                int userId = rs.getInt("User_id");
                                                String username = rs.getString("username");
                                                String u_email = rs.getString("email_address");
                                                String u_password = rs.getString("password");
                                                String phone = rs.getString("phone_no");
                                                Timestamp timestamp = rs.getTimestamp("datestamp");
                                        
                                                // Format the output
                                                String output = String.format("User ID: %d\nUsername: %s\nEmail: %s\nPassword: %s\nPhone: %s\nTimestamp: %s\n", 
                                                                            userId, username, u_email, u_password, phone, timestamp);
                                                System.out.println(output);
                                            }


                                            break;

                                        case 2:
                                            // Delete users
                                            Scanner sc = new Scanner(System.in);
                                            System.out.print("Enter the user that you want to delete: ");
                                            String d_user = sc.nextLine();

                                            String selectUserQuery0 = "SELECT user_id FROM Users WHERE username = '" + d_user + "'";

                                            ResultSet resultSet07 = statement.executeQuery(selectUserQuery0);
                                            // int userID = resultSet0.getInt("user_id");
                                            int userID07;

                                            if (resultSet07.next()) {
                                                userID07 = resultSet07.getInt("user_id");
                                                String inboxDeleteQuery = "DELETE FROM Inbox WHERE sender_id = " + userID07 + " OR receiver_id = " + userID07;
                                                statement.executeUpdate(inboxDeleteQuery);
                                                
                                                String deleteQuery = "DELETE FROM users WHERE User_id = '" + userID07 + "'";
                                                statement.executeUpdate(deleteQuery);

                                                System.out.println("User with name " + d_user + " has been deleted successfully.");
                                            }

                                            break;

                                        case 3:
                                        // See all messages
                                        String m_query = "SELECT * FROM Inbox";
                                        Statement stmt = connection.createStatement();
                                        ResultSet m_rs = stmt.executeQuery(m_query);
                                        while(m_rs.next()) {
                                            int senderId = m_rs.getInt("sender_id");
                                            int receiverId = m_rs.getInt("receiver_id");
                                            String subject = m_rs.getString("subject");
                                            String message = m_rs.getString("message");
                                            Timestamp dateReceived = m_rs.getTimestamp("date_received");
                                            
                                            final String ANSI_YELLOW = "\u001B[33m";
                                            //final String ANSI_CYAN = "\u001B[36m";
                                            //final String ANSI_RESET = "\u001B[0m";

                                            System.out.println(ANSI_YELLOW + "Sender ID: " + senderId);
                                            System.out.println("Receiver ID: " + receiverId);
                                            System.out.println("Subject: " + subject);
                                            System.out.println(ANSI_CYAN + "Message: " + message);
                                            System.out.println("Date Received: " + dateReceived + ANSI_RESET);
                                            System.out.println();

                                        }
                                        
                                        break;

                                        case 4:
                                        // See Stats of message send and recived by the users
                                        // Get the user_id, username, and email_address for every user
                                        String getUsersQuery1 = "SELECT user_id, username, email_address FROM Users";
                                        Statement getUsersStatement = connection.createStatement();
                                        ResultSet usersResult = getUsersStatement.executeQuery(getUsersQuery1);
                                        
                                        // Iterate over each user and get their message statistics
                                        while (usersResult.next()) {
                                            int userId = usersResult.getInt("user_id");
                                            String username = usersResult.getString("username");
                                            String emailAddress = usersResult.getString("email_address");
                                            
                                            // Get the number of messages received by the user
                                            String countReceivedQuery = "SELECT COUNT(*) FROM Inbox WHERE receiver_id = ?";
                                            PreparedStatement countReceivedStatement = connection.prepareStatement(countReceivedQuery);
                                            countReceivedStatement.setInt(1, userId);
                                            ResultSet countReceivedResult = countReceivedStatement.executeQuery();
                                            countReceivedResult.next();
                                            int numReceived = countReceivedResult.getInt(1);
                                            
                                            // Get the number of messages sent by the user
                                            String countSentQuery = "SELECT COUNT(*) FROM Inbox WHERE sender_id = ?";
                                            PreparedStatement countSentStatement = connection.prepareStatement(countSentQuery);
                                            countSentStatement.setInt(1, userId);
                                            ResultSet countSentResult = countSentStatement.executeQuery();
                                            countSentResult.next();
                                            int numSent = countSentResult.getInt(1);
                                            
                                            // Print the statistics for the user
                                            System.out.println("User: " + username + " (" + emailAddress + ")");
                                            System.out.print("Number of messages received: ");
                                            for(int i = 0;i < numReceived; i++) {
                                                System.out.print("|");
                                            }
                                            System.out.print(" - "+numReceived);
                                            System.out.println();
                                            
                                            System.out.print("Number of messages sent: ");
                                            for(int i = 0;i < numSent; i++) {
                                                System.out.print("|");
                                            }
                                            System.out.print(" - "+numSent);
                                            System.out.println();
                                        }                            
                                        break;
                                        
                                        default:
                                        System.out.println("Invalid choice.. Please try other choice");
                                        break;
                                    }

                                }while(a_options != -1);





                            }
                            else{
                                // Invalid email or password
                                final String ANSI_PURPLE = "\u001B[35m";
                                //final String ANSI_RESET = "\u001B[0m";
                                
                                System.out.println(ANSI_PURPLE + "+----------------------------------+");
                                System.out.println("|           Login Failed           |");
                                System.out.println("+----------------------------------+");
                                System.out.println("| Invalid email or password.       |");
                                System.out.println("| Please try again.                |");
                                System.out.println("+----------------------------------+" + ANSI_RESET);
                                
                            }





                            break;

                            case 3:
                            
                            Scanner scanner = new Scanner(System.in);
                            String UA;
                            System.out.println("Are you going to enter user or admin: ");
                            UA = scanner.nextLine();

                            if(UA.equals("user")){
                                final String ANSI_YELLOW = "\u001B[33m";
                                final String ANSI_RESET1 = "\u001B[0m";
                                
                                System.out.println("+--------------------------------+");
                                System.out.println("| New User                       |");
                                System.out.println("+--------------------------------+");
                                System.out.println("Enter the following details:");
                                System.out.print(ANSI_YELLOW + "Username: " + ANSI_RESET1);
                                
                                String username = scanner.nextLine();
                                
                                // Check if the username already exists in the database
                                String checkUsernameQuery = "SELECT * FROM Users WHERE username = ?";
                                PreparedStatement checkUsernameStatement = connection.prepareStatement(checkUsernameQuery);
                                checkUsernameStatement.setString(1, username);
                                ResultSet rs = checkUsernameStatement.executeQuery();
                                if (rs.next()) {
                                    System.out.println("Username already exists. Please choose a different username.");
                                } else {
                                    System.out.print("Password: ");
                                    String password2 = scanner.nextLine();
                                    System.out.print("Email Address: ");
                                    String email2 = scanner.nextLine();
                                    System.out.print("Phone Number: ");
                                    String phone = scanner.nextLine();
                                    String addUserQuery = "INSERT INTO Users (username, password, email_address, phone_no) VALUES (?, ?, ?, ?)";
                                    PreparedStatement addUserStatement = connection.prepareStatement(addUserQuery);
                                    addUserStatement.setString(1, username);
                                    addUserStatement.setString(2, password2);
                                    addUserStatement.setString(3, email2);
                                    addUserStatement.setString(4, phone);
                                    addUserStatement.executeUpdate();
                                    final String ANSI_GREEN = "\u001B[32m";
                                    ///ANSI_RESET = "\u001B[0m";

                                    System.out.println("+--------------------------------+");
                                    System.out.println("| " + ANSI_GREEN + "User added successfully!" + ANSI_RESET1 + "       |");
                                    System.out.println("+--------------------------------+");

                                }
                            }

                            else if(UA.equals("admin")){
                            final String ANSI_YELLOW = "\u001B[33m";
                            final String ANSI_RESET2 = "\u001B[0m";
                            
                            System.out.println("+--------------------------------+");
                            System.out.println("| " + ANSI_YELLOW + "New Admin" + ANSI_RESET2 + "                      |");
                            System.out.println("+--------------------------------+");
                            System.out.println("Enter the following details:");
                            System.out.print("Enter admin name: ");
                            String admin_name = scanner.nextLine();
                            System.out.print("Enter admin Email Address: ");
                            String admin_email = scanner.nextLine();
                            System.out.print("Enter admin Password: ");
                            String admin_pass = scanner.nextLine();
                            
                            String addAdminQuery = "INSERT INTO admin (admin_name, admin_password, admin_mail_address) VALUES (?, ?, ?)";
                            PreparedStatement addAdminStatemeny = connection.prepareStatement(addAdminQuery);
                            addAdminStatemeny.setString(1, admin_name);
                            addAdminStatemeny.setString(2, admin_pass);
                            addAdminStatemeny.setString(3, admin_email);
                            addAdminStatemeny.executeUpdate();
                            final String ANSI_GREEN = "\u001B[32m";
                            //final String ANSI_RESET = "\u001B[0m";

                            System.out.println("+--------------------------------+");
                            System.out.println("| " + ANSI_GREEN + "Admin added successfully!" + ANSI_RESET2 + "       |");
                            System.out.println("+--------------------------------+");


                            }
                            break;
                            
                        
                        case 4:
                            // Forgot Password
                            final String ANSI_CYAN1 = "\u001B[36m";
                            final String ANSI_RESET3 = "\u001B[0m";
                            
                            System.out.println("+--------------------------------+");
                            System.out.println("| " + ANSI_CYAN1 + "Forgot Password" + ANSI_RESET3 + "                |");
                            System.out.println("+--------------------------------+");
                            System.out.println("Please enter your email address:");
                            
                                String email3 = System.console().readLine();
                                System.out.println("Please enter a new password:");
                                String newPassword = new String(System.console().readPassword());
                                String updateQuery = "UPDATE Users SET password = '" + newPassword + "' WHERE email_address = '" + email3 + "'";
                                int numRowsUpdated = statement.executeUpdate(updateQuery);
                                if (numRowsUpdated > 0) {
                                    // Password reset successful
                                    System.out.println("Password reset successful!");
                                } else {
                                    // Password reset failed
                                    System.out.println("Password reset failed. Please try again.");
                                }
                                break;

                        case 5:
                                final String ANSI_PURPLE = "\u001B[35m";
                                final String ANSI_RESET4 = "\u001B[0m";
                                
                                System.out.println(ANSI_PURPLE + "+--------------------------------+");
                                System.out.println("| Thank you for using CMail      |");
                                System.out.println("+--------------------------------+" + ANSI_RESET4);
                                System.exit(0);
                                
                                

                        default:
                                System.out.println("Invalid choice. Try again");
                                break;

                    }
        } while(choice != -1 );
                    
                    
                    // Process the user's choice
                    
                    
                    // Close the connection
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                
            }
        }

                        