<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script type="text/javascript" src="JS/GetCurrentLoc.js"></script>
<h1>와이파이 정보 구하기</h1>
<div></div>
<a href="./">홈</a> <span>|</span> <a href="/history">위치 히스토리 목록</a> <span>|</span> <a href="/loadwifi">Open API 와이파이 정보 가져오기</a>
<form action="/main">
    <label>LAT: </label>
    <input type="text" id="lat" name="lat" placeholder="0.0" required value="${param.lat}"/>
    <label>LNT: </label>
    <input type="text" id="lnt" name="lnt" placeholder="0.0" required value="${param.lnt}"/>
    <button type="button" onclick="getLocation()">내 위치 가져오기</button>
    <input type="submit" value="근처 WIFI 정보 가져오기"/>
</form>

<table border = "1 solid" width="100%">
    <thead>
    <tr>
        <th>거리(KM)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <%if (request.getAttribute("list") == null) {%>
    <tr>
        <td colspan="17" align = "center">
            위치 정보를 입력한 후에 조회해 주세요.
        </td>
    </tr>
    <%
    } else {
        for (String s : (ArrayList<String>) request.getAttribute("list")) {%>
    <tr>
        <td colspan="17" align = "center">
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