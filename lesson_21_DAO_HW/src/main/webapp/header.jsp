<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <!--[if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
    <title>Наше учебное учреждение</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="./static/style.css" rel="stylesheet">
</head>

<body>

<div class="wrapper">

    <header class="header">
        <div class="main_header"Наше учебное учреждение></div>
        <div class="logout">
        <c:if test="${sessionScope.user !=null}" >
            <c:if test="${sessionScope.userFName !=null && sessionScope.userLName !=null}" >
                <strong> <c:out value="${sessionScope.userFName}"/> </strong>
                <c:out value="   "/>
                <strong> <c:out value="${sessionScope.userLName}"/></strong>
            </c:if>
            <a href="${pageContext.request.contextPath}/login?act=out">Выйти</a>
        </c:if>
        </div>

    </header><!-- .header-->
    <div class="middle">