import java.util.*;

public class QuizDataStore {
    private static final List<Map<String, Object>> scores = new ArrayList<>();

    static {
        // Sample scores - you can modify or connect to DB later
        addScore("user123", 7);
        addScore("spoorthi", 9);
        addScore("john_doe", 5);
    }

    public static synchronized void addScore(String username, int score) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("username", username);
        entry.put("score", score);
        entry.put("timestamp", new Date());
        scores.add(entry);
    }

    public static synchronized List<Map<String, Object>> getAllScores() {
        return new ArrayList<>(scores); // Return a copy to avoid modification
    }
}
