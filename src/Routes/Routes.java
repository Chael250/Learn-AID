package Routes;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Routes {
    private final Connection connection;
    private final Scanner scan;

    public Routes(Connection connect){
        connection = connect;
        scan = new Scanner(System.in);
    }

    public void getStudents() throws SQLException {
        var statement = connection.createStatement();
        String query = "SELECT * FROM student";
        ResultSet results = statement.executeQuery(query);
        while(results.next()){
            System.out.println("The student " + results.getInt(1) + " is called " + results.getString(2) + " and is " + results.getString(3) + " years old");
        }
    }

    public void getAStudent() throws SQLException {
        var statement = connection.createStatement();
        System.out.println("ENTER THE INDEX TO VIEW: ");
        int index = scan.nextInt();
        String query = "SELECT * FROM student WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, index);
        ResultSet newStudent = preparedStatement.executeQuery();
        if(newStudent.next()){
            System.out.println("The student " + newStudent.getInt(1) + " is called " + newStudent.getString(2) + " and is " + newStudent.getString(3) + " years old");
        } else {
            System.out.println("No next");
        }
    }

    public void removeStudent() throws SQLException {
        var statement = connection.createStatement();
        System.out.println("ENTER THE INDEX TO DELETE OR TYPE ALL IF YOU WANT TO DELETE EVERY THING: ");
        String input = scan.nextLine();

        if(input.equalsIgnoreCase("ALL")){
            String query = "TRUNCATE TABLE student";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("ALL DATA DELETED SUCCESSFULLY ");
        } else {
            try{
                int id = Integer.parseInt(input);
                String query = "DELETE FROM student WHERE id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,id);
                int deletedStudent = preparedStatement.executeUpdate();
                System.out.println("STUDENT DELETED SUCCESSFULLY" + deletedStudent);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid input");
            }
        }

    }
    public void editStudent() throws SQLException {
        var statement = connection.createStatement();
        System.out.println("ENTER THE INDEX TO EDIT: ");
        int index = scan.nextInt();
        scan.nextLine();
        System.out.println("GOT IT!!!!");
        System.out.println("ENTER THE NAME TO UPDATE: ");
        String newName = scan.nextLine();
        System.out.println("ENTER THE AGE TO UPDATE: ");
        int newAge = scan.nextInt();
        String query = "UPDATE student SET name = ?, age = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, newAge);
        preparedStatement.setInt(3,index);
        int updatedStudent = preparedStatement.executeUpdate();
        System.out.println("UPDATED SUCCESSFULLY" + updatedStudent);
    }
    public void createStudents() throws SQLException {
        var statement = connection.createStatement();
        System.out.println("ENTER THE NAME OF THE STUDENT: ");
        String name = scan.nextLine();
        System.out.println("ENTER THE AGE OF THE STUDENT: ");
        int age = scan.nextInt();
        String query = "INSERT INTO student (name, age) VALUES (?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,age);
        int createdStudent = preparedStatement.executeUpdate();
        System.out.println("CREATED SUCCESSFULLY" + createdStudent);
    }
}
