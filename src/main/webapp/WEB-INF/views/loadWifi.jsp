<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi 저장 성공</title>
    <style>
        #wrapper {
            width: 80%;
            margin: auto;
        }

        #txtContact {
            width: fit-content;
            margin: auto;
        }

        #home {
            width: 100px;
            margin: auto;
            display: block;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <h1 id="txtContact">${wifiCount} 개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a id="home" href="/">홈</a>
</div>
</body>
</html>
