<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div></div>
<a href="./">홈</a> <span>|</span> <a href="./" methods="get">위치 히스토리 목록</a> <span>|</span> <a href="./" methods="get">Open API 와이파이 정보 가져오기</a>
<form>
    <label>LAT: </label>
    <input type = "text" name = "lat" required value="${param.q}"/>
    <label>LNT: </label>
    <input type = "text" name = "lnt" required value="${param.q}"/>
    <input type="submit" value="확인">
    <button type="button">내 위치 가져오기</button>
    <button type="button">근처 WIFI 정보 가져오기</button>
</form>>
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
        <th>서비스</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>Y좌표</th>
        <th>X좌표</th>
        <th>작업</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
</body>
</html>