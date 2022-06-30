<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.WifiInfoDto" %>
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
            font-size: 80%;

        }

        th {
            background-color: #26ab71;
            color: aliceblue;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="../../JS/GetCurrentLoc.js?ver=1"></script>
<h1>와이파이 정보 구하기</h1>
<p></p>
<a href="/">홈</a> <span>|</span> <a href="/history">위치 히스토리 목록</a> <span>|</span> <a href="/loadWifi">Open API 와이파이 정보
    가져오기</a>
<p></p>
<form action="/search">
    <label>LAT: </label>
    <input type="text" id="lat" name="lat" placeholder="0.0" required value="${param.lat}"/>
    <label>LNT: </label>
    <input type="text" id="lnt" name="lnt" placeholder="0.0" required value="${param.lnt}"/>
    <button type="button" onclick="getLocation()">내 위치 가져오기</button>
    <input type="submit" value="근처 WIFI 정보 가져오기"/>
</form>
<table>
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
    <%
        for (WifiInfoDto info : (ArrayList<WifiInfoDto>) request.getAttribute("list")) {%>
    <tr>
        <td>
            <%=info.getDistance()%>
        </td>
        <td>
            <%=info.getX_SWIFI_MGR_NO()%>
        </td>
        <td>
            <%=info.getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <%=info.getX_SWIFI_MAIN_NM()%>
        </td>
        <td>
            <%=info.getX_SWIFI_ADRES1()%>
        </td>
        <td>
            <%=info.getX_SWIFI_ADRES2()%>
        </td>
        <td>
            <%=info.getX_SWIFI_INSTL_FLOOR()%>
        </td>
        <td>
            <%=info.getX_SWIFI_INSTL_TY()%>
        </td>
        <td>
            <%=info.getX_SWIFI_INSTL_MBY()%>
        </td>
        <td>
            <%=info.getX_SWIFI_SVC_SE()%>
        </td>
        <td>
            <%=info.getX_SWIFI_CMCWR()%>
        </td>
        <td>
            <%=info.getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td>
            <%=info.getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td>
            <%=info.getX_SWIFI_REMARS3()%>
        </td>
        <td>
            <%=info.getLAT()%>
        </td>
        <td>
            <%=info.getLNT()%>
        </td>
        <td>
            <%=info.getWORK_DTTM()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>