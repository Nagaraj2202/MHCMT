<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%
    String user = (String) session.getAttribute("username");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<h2>Welcome, <%= user %>!</h2>
<a href="logout.jsp">Logout</a>
