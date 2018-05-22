<%--
  Created by IntelliJ IDEA.
  User: Лангустин
  Date: 08.05.2018
  Time: 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.log4j.Logger" %>
<%! static Logger logger = Logger.getLogger(register_jsp.class); %>

<%@ include file="header.jsp" %>
<% logger.debug("Start open page"); %>
<%=request.getAttribute("message") + "<br>"%>
<form action="${pageContext.request.contextPath}/register" method="post">
    <table>
        <tr>
            <td>
                <input type="text" value="" name="first name" placeholder="Имя"> <sup>*</sup><br>
            </td>
        </tr>

        <tr>
            <td>
                <input type="text" value="" name="last name" placeholder="Фамилия"> <sup>*</sup><br>
            </td>
        </tr>

        <tr>
            <td>
                <input type="text" value="" name="patronymic" placeholder="Отчество"> <sup>**</sup><br>
            </td>
        </tr>

        <tr>
            <td>
                <input type="email" value="" name="email" placeholder="e-mail"> <sup>*</sup><br>
            </td>
        </tr>

        <tr>
            <td>
                <input type="text" value="" name="phone" placeholder="номер телефона"><br>
            </td>
        </tr>

        <tr>
            <td>
                <input type="text" value="" name="login" placeholder="логин"> <sup>*</sup><br>
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" value="" name="password1" placeholder="пароль"> <sup>*</sup>
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" value="" name="password2" placeholder="повторите пароль"><sup>*</sup>
            </td>
        </tr>

        <tr>
            <sup>*</sup> - поля, обязательные для заполнения.<br>
            <sup>**</sup> - поле обязательно для заполнения при наличии отчества в документах.<br>
        </tr>
    </table>
    <input type="submit" value="OK">
</form>
<% logger.debug("End open page"); %>
<%@ include file="sidebar.jsp" %>
<%@ include file="footer.jsp" %>
