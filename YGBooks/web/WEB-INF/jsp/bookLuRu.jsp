<%--
  Created by IntelliJ IDEA.
  User: 权
  Date: 2021/8/18
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/luRu">
        ID：<input name="bid" type="number">
        书名：<input name="bookName" type="text">
        作者：<input type="text" name="author">
        花费：<input name="cost" type="number">
        曾经花费：<input name="preCost" type="number">
        详细信息：<input name="de" type="text">
        <input type="submit">
    </form>
</body>
</html>
