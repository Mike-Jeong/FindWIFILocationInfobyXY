<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<div></div>
<a href="./">홈</a> <span>|</span> <a href="/history">위치 히스토리 목록</a> <span>|</span> <a href="/loadwifi">Open API 와이파이 정보 가져오기</a>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <%if (request.getAttribute("historylist") == null) {%>
    <tr>
        <td colspan="4">
            아직 검색한 와이파이 정보가 없습니다.
        </td>
    </tr>
    <%
    } else {
        for (String s : (ArrayList<String>) request.getAttribute("historylist")) {%>
    <tr>
        <td colspan="5">
            <%=s%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>