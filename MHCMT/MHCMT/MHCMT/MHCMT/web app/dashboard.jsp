<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - MHCMCT</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
    <a href="logout.jsp">Logout</a>
</body>
</html>