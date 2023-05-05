import java.sql.*;
import java.util.*;
import java.sql.Date;

public class login {
   

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test1"; // Replace "mydatabase" with your database name
        String user = "root"; // Replace "username" with your database username
        String password1 = "nehaniki123@J"; // Replace "password" with your database password
        
        try {
            // Connect to the database
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
                        System.out.println("+----------------------------------+");
                        System.out.println("|           Login Success          |");
                        System.out.println("+----------------------------------+");
                        System.out.println("| Welcome back, " + resultSet.getString("username") + "!         |");
                        System.out.println("+----------------------------------+");
                        // System.out.println("Login successful!");
                        
                        // Code to view inbox and send messages.

                        int options;
                        do{
                            System.out.println("+----------------------------------+");
                            System.out.println("|         Welcome to CMail         |");
                            System.out.println("+----------------------------------+");
                            System.out.println("| Options:                         |");
                            System.out.println("|        1. View Inbox             |");
                            System.out.println("|        2. Send messages          |");
                            System.out.println("|        3. Logout (Press -1)      |");
                            System.out.println("+----------------------------------+");

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
                                            System.out.println("Message ID: " + messageID);
                                            System.out.println("Sender ID: " + senderID);
                                            System.out.println("Subject: " + subject);
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
                                        System.out.print("Enter recipient's email address: ");
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
                                        System.out.print("Enter message subject: ");
                                        String subject = scanner.nextLine();
                                        System.out.print("Enter message body: ");
                                        String messageBody = scanner.nextLine();

                                        // Define the SQL query for inserting a new message into the Inbox table
                                        String insertInboxQuery = "INSERT INTO Inbox (sender_id, receiver_id, subject, message, date_received) VALUES (" 
                                        + userIDr + "," + receiverID + ",'" + subject + "','" + messageBody + "',NOW())";

                                        // Execute the query to insert the new message into the Inbox table
                                        statement.executeUpdate(insertInboxQuery);

                                        System.out.println("Message sent successfully!");
                                        break;

                                    
                                    
                                    default:
                                        System.out.println("Invalid choice!!");
                                        break;
                        

                            }



                        }while(options != -1);


                    } else {
                        // Invalid email or password
                        System.out.println("+----------------------------------+");
                        System.out.println("|           Login Failed           |");
                        System.out.println("+----------------------------------+");
                        System.out.println("| Invalid email or password.       |");
                        System.out.println("| Please try again.                |");
                        System.out.println("+----------------------------------+");
                    }
                    break;

                   