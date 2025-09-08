import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "yourpassword");

      PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
      ps.setString(1, username);
      ps.setString(2, email);
      ps.setString(3, password);
      ps.executeUpdate();

      response.sendRedirect("login.html");
    } catch (SQLIntegrityConstraintViolationException e) {
      out.println("<h3 style='color:red;'>Username or Email already exists!</h3>");
    } catch (Exception e) {
      out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
    }
  }
}
