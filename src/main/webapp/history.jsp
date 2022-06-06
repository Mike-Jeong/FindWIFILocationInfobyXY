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
<a href="./">홈</a> <span>|</span> <a href="./" methods="get">위치 히스토리 목록</a> <span>|</span> <a href="./" methods="get">Open API 와이파이 정보 가져오기</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <%if (request.getAttribute("list") == null) {%>
    <tr>
        <td colspan="4">
            아직 검색한 와이파이 정보가 없습니다.
        </td>
    </tr>
    <%
    } else {
        for (String s : (ArrayList<String>) request.getAttribute("history")) {%>
    <p><%=s%>
    </p>
    <%
            }
        }
    %>
</table>
</body>
</html>