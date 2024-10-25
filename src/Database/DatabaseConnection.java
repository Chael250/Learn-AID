package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    private final String url;
    private final String username;
    private final String password;

   public DatabaseConnection(String url, String username, String password){
       this.url = url;
       this.username = username;
       this.password = password;
   }

   public Connection connect() throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       return DriverManager.getConnection(url, username, password);
   }
}
