MentalHealthServletDemo - ChatLoggerServlet Setup

1. Place the real JAR files in models/lib/:
   - javax.servlet-api-4.0.1.jar
   - json-20230618.jar (only needed if you use JSONObject)

2. To compile:
   javac -cp "models/lib/*" -d out src/ChatLoggerServlet.java

3. To deploy:
   Deploy out/ChatLoggerServlet.class to a servlet container like Apache Tomcat.

NOTE: Do not include your own HttpServletRequest or HttpServletResponse class.