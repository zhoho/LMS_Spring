<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>My JSP Page</title>
    <link rel="stylesheet" type="text/css" href="${context}/resources/css/style.css">
</head>
<body>
<h1>Welcome to My JSP Page!</h1>
<main>
    <img style="width: 20%; height: auto; padding: 20px;" src="${context}/resources/image/fish.png" alt="fish">

    <div class="container">
        <c:if test="${empty name}">
            <form id="myForm" action="${context}/form" method="post">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your name">
                <button type="submit">Submit</button>
            </form>
        </c:if>
        <c:if test="${not empty name}">
            <div id="message">
                <h2>Hello, ${name}!</h2>
            </div>
            <button type="button">
                <a href="${context}/">Retry</a>
            </button>
        </c:if>
    </div>
</main>
<script src="${context}/static/js/script.js"></script>
</body>
</html>