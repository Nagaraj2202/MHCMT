@echo off
set CLASSPATH=lib\mysql-connector-j-8.0.xx.jar;out
javac -d out -cp %CLASSPATH% src\DBConnection.java src\ChatLogger.java
java -cp %CLASSPATH% ChatLogger
pause