CREATE TABLE login_activity (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100),
  login_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chatbot_activity (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100),
  message_type ENUM('user','bot'),
  timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quiz_activity (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100),
  score INT,
  completed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
