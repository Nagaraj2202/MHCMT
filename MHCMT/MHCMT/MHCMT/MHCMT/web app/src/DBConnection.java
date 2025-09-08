import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mh_chatbot"; // database
        String username = "root"; // or your MySQL username
        String password = "your_password"; // your password

        Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
        return DriverManager.getConnection(url, username, password);
    }
}
