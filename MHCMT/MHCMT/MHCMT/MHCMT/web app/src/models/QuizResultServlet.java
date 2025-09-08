import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/QuizResultServlet")
public class QuizResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

        String userId = request.getParameter("user_id");
        int score = Integer.parseInt(request.getParameter("score"));

        QuizDataStore.addScore(userId, score);

        response.setContentType("text/plain");
        response.getWriter().write("Score submitted");
    }
}
