import Database.DatabaseConnection;
import Routes.Routes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcDemo";
        String username = "root";
        String password = "";
        Scanner scan = new Scanner(System.in);
        try{
            DatabaseConnection connect = new DatabaseConnection(url, username, password);
            var dbConnection = connect.connect();
            Routes routes = new Routes(dbConnection);
            System.out.println("-------------------------------------------");
            System.out.println("           WELCOME TO LEARN-AID            ");
            System.out.println("-------------------------------------------");
            System.out.println("1. CREATE STUDENT");
            System.out.println("2. READ STUDENTS");
            System.out.println("3. READ A STUDENT");
            System.out.println("4. UPDATE A STUDENT");
            System.out.println("5. DELETE A STUDENT");
            System.out.println("6. LOGOUT");
            System.out.println("ENTER THE OPTION: ");
            int option = scan.nextInt();
            scan.nextLine();

            switch (option){
                case 1:
                    routes.createStudents();
                    break;
                case 2:
                    routes.getStudents();
                    break;
                case 3:
                    routes.getAStudent();
                    break;
                case 4:
                    routes.editStudent();
                    break;
                case 5:
                    routes.removeStudent();
                    break;
                case 6:
                    System.out.println("Logging Out... See you next time ;)");
                    break;
                default:
                    System.out.println("STUPID MAN, TYPE FROM 1-6.");
                    break;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}