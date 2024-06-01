<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
    <c:set var="pageTitle" value="Home" scope="page" /> <%-- pageContext.setAttribute("pageTitle", "Home"); --%>
    <%-- 끝나는 태그 필요 X. --%>
    <%-- "pageTitle"의 값을 "Home"으로 설정(헤더에 "Home" 제목이 나오게 하기 위해서). --%> <%-- scope에서 기본값은 "page" --%>
        <%@ include file="./fragments/header.jspf" %> <%-- "./"(점 1개)은 같은 경로 --%>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

