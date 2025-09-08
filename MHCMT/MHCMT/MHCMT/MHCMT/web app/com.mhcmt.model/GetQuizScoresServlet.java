import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;

@WebServlet("/GetQuizScoresServlet")
public class GetQuizScoresServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Map<String, Object>> scores = QuizDataStore.getAllScores();

        Gson gson = new Gson();
        out.print(gson.toJson(scores));
        out.flush();
    }
}
