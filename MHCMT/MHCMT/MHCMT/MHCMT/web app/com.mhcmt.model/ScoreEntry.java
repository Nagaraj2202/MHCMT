public class ScoreEntry {
    private String userId;
    private int score;

    public ScoreEntry(String userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }
}