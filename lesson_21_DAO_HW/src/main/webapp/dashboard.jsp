<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.apache.log4j.Logger"%>
<%! static Logger logger = Logger.getLogger(dashboard_jsp.class); %>


<% logger.debug("Start open page"); %>
<%@ include file="header.jsp"%>
<div class="container">
    <main class="content">
        <table>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/login">Расписание   </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/login">Оценки   </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/login">Домашнее задание   </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/login">Одногруппники   </a>

                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/register">Куратор</a>
                </td>
            </tr>
        </table>
    </main><!-- .content -->
</div><!-- .container-->

<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
<% logger.debug("End open page"); %>



