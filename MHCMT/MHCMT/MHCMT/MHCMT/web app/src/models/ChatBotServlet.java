// File: ChatBotServlet.java
package com.mhcmt.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/ChatBotServlet")
public class ChatBotServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mhcmt";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password"; // Change as per your setup

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String userId = request.getParameter("user_id");
        String userMessage = request.getParameter("message");

        PrintWriter out = response.getWriter();
        try {
            // Log the user message to chat_logs
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement logStmt = conn.prepareStatement("INSERT INTO chat_logs (user_id, type, message, timestamp) VALUES (?, 'user', ?, NOW())");
            logStmt.setString(1, userId);
            logStmt.setString(2, userMessage);
            logStmt.executeUpdate();

            // Get a random mental health reply
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT message FROM mental_health_replies ORDER BY RAND() LIMIT 1");

            String botReply = "I’m here to listen.";
            if (rs.next()) {
                botReply = rs.getString("message");
            }

            // Log bot reply
            PreparedStatement botStmt = conn.prepareStatement("INSERT INTO chat_logs (user_id, type, message, timestamp) VALUES (?, 'bot', ?, NOW())");
            botStmt.setString(1, userId);
            botStmt.setString(2, botReply);
            botStmt.executeUpdate();

            conn.close();
            out.print(botReply);

        } catch (Exception e) {
            out.print("Sorry, I couldn’t respond right now.");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
