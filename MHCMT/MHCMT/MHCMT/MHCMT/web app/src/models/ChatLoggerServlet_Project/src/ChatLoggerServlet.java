package com.mhcmt.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/ChatLoggerServlet")
public class ChatLoggerServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mhcmt";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String message = request.getParameter("message");
        String type = request.getParameter("type");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO chat_logs (user_id, type, message, timestamp) VALUES (?, ?, ?, NOW())"
            );
            stmt.setString(1, userId);
            stmt.setString(2, type);
            stmt.setString(3, message);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}