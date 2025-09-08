import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/QuizResultServlet")
public class QuizResultServlet extends HttpServlet {
    private static final Map<String, ScoreEntry> scoreMap = new HashMap<>();
    private static int quizAttempts = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        int score = Integer.parseInt(request.getParameter("score"));

        synchronized (scoreMap) {
            if (!scoreMap.containsKey(userId)) {
                quizAttempts++;  // Only count new users
            }
            scoreMap.put(userId, new ScoreEntry(userId, score));
        }

        response.setContentType("text/plain");
        response.getWriter().println("Score recorded for: " + userId);
    }

    public static int getTotalQuizAttempts() {
        return quizAttempts;
    }

    public static Collection<ScoreEntry> getScores() {
        return scoreMap.values();
    }
}
