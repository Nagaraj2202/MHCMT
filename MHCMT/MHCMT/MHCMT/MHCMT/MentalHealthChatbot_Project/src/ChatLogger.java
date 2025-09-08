import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChatLogger {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO chat_logs (user_id, type, message) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "user_001");
            ps.setString(2, "user");
            ps.setString(3, "I'm feeling stressed lately.");

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Message logged successfully!");
            } else {
                System.out.println("Message not logged.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
