import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/QuizTrackerServlet")
public class QuizTrackerServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mhcmt_analytics";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        int score = Integer.parseInt(request.getParameter("score"));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO quiz_activity (user_id, score, completed_at) VALUES (?, ?, NOW())"
            );
            stmt.setString(1, userId);
            stmt.setInt(2, score);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
