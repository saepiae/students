<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.log4j.Logger"%>
<%! static Logger logger = Logger.getLogger(login_jsp.class); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="header.jsp"%>
<div class="container">
    <main class="content">
        <% logger.debug("Start open page"); %>
        <c:if test="${requestScope.message eq 'instruct'}" >
            <c:out value="Введите свои логин и пароль"/>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" value="" name="name" placeholder="Введите свой логин"><br>
            <input type="password" value="" name="password" placeholder="Введите свой пароль"><br>
            <c:choose>
                <c:when test="${param.errorMsg eq 'authError'}">
                    Неправильный логин и/или пароль
                </c:when>
                <c:when test="${param.errorMsg eq 'noAuth'}">
                    Необходимо пройти идентификацию<br>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${param.action  eq 'passRecovery'}">
                    Пользователь с такими данными уже зарегистрирован. Обратитесь в деканат для восстановления доступа<br>
                </c:when>
                <c:when test="${param.action eq 'modError'}">
                    К сожалению, мы не можем вас зарегистрировать. Если Вы являетесь сотрудником или студентом нашего учебного учреждения, обратитесь в деканат<br>
                </c:when>
                <c:when test="${param.action eq 'newUser'}">
                    Отлично, Вы зарегистрировались. Авторизуйтесь, пожалуйста,  на сайте<br>
                </c:when>
            </c:choose>
            <br>
            <input type="submit" value="OK">
        </form>
        <% logger.debug("End open page"); %>
    </main><!-- .content -->
</div><!-- .container-->

<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
