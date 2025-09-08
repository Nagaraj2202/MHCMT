## 📄 Sample MySQL Schema
```sql
CREATE TABLE chat_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100),
    type ENUM('user', 'bot'),
    message TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mental_health_replies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL
);
```