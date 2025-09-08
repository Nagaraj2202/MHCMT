package com.mhcmt.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;

import com.mhcmt.model.ScoreEntry;  // Optional: if ScoreEntry is needed
import com.mhcmt.servlets.QuizResultServlet; // Import if needed for merging in-memory tracking

@WebServlet("/AnalyticsServlet")
public class AnalyticsServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mhcmt_analytics";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password"; // update as per your setup

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id"); // optional: send user-specific stats

        JSONObject result = new JSONObject();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            // 1. Total logins
            PreparedStatement loginStmt = conn.prepareStatement(
                "SELECT COUNT(*) AS login_count FROM login_activity" + (userId != null ? " WHERE user_id=?" : "")
            );
            if (userId != null) loginStmt.setString(1, userId);
            ResultSet loginRs = loginStmt.executeQuery();
            if (loginRs.next()) result.put("login_count", loginRs.getInt("login_count"));

            // 2. Total chatbot messages
            PreparedStatement chatStmt = conn.prepareStatement(
                "SELECT COUNT(*) AS chat_count FROM chatbot_activity WHERE message_type='user'" + (userId != null ? " AND user_id=?" : "")
            );
            if (userId != null) chatStmt.setString(1, userId);
            ResultSet chatRs = chatStmt.executeQuery();
            if (chatRs.next()) result.put("chat_messages", chatRs.getInt("chat_count"));

            // 3. Quiz attempts + average score
            PreparedStatement quizStmt = conn.prepareStatement(
                "SELECT COUNT(*) AS quiz_attempts, AVG(score) AS avg_score FROM quiz_activity" + (userId != null ? " WHERE user_id=?" : "")
            );
            if (userId != null) quizStmt.setString(1, userId);
            ResultSet quizRs = quizStmt.executeQuery();
            if (quizRs.next()) {
                result.put("quiz_attempts", quizRs.getInt("quiz_attempts"));
                result.put("avg_score", quizRs.getDouble("avg_score"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "Analytics fetch failed");
        }

        response.setContentType("application/json");
        response.getWriter().write(result.toString());
    }
}
