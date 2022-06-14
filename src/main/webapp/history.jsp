<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.History" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        table, td, th {
            border: 1px solid lightgrey;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 8px;
        }
        td, th {
            padding: 8px;

        }
        th{
            background-color: #26ab71;
            color: aliceblue;
            font-size: 80%;
        }
        td{
            text-align: center;
        }
    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<p></p>
<a href="./">홈</a> <span>|</span> <a href="/history">위치 히스토리 목록</a> <span>|</span> <a href="/loadwifi">Open API 와이파이 정보 가져오기</a>

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
    <%if (request.getAttribute("historylist") == null) {%>
    <tr>
        <td colspan="5">
            아직 검색한 와이파이 정보가 없습니다.
        </td>
    </tr>
    <%
    } else {
        int i = 1;
        for (History history : (ArrayList<History>) request.getAttribute("historylist")) {%>
    <tr>
        <td>
            <%=i%>
        </td>
        <td>
            <%=history.getLAT()%>
        </td>
        <td>
            <%=history.getLNT()%>
        </td>
        <td>
            <%=history.getDate()%>
        </td>
        <td>
            <form action="/history/delete" method="get">
                <input type="hidden" name="lat" value="<%=history.getLAT()%>">
                <input type="hidden" name="lnt" value="<%=history.getLNT()%>">
                <input type="hidden" name="date" value="<%=history.getDate()%>">
                <button type="submit">삭제</button>
            </form>
        </td>
    </tr>
    <%
       i++;     }
        }
    %>
</table>
</body>
</html>