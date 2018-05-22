<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.log4j.Logger"%>
<%@ page import="ru.innopolis.stc9.servlets.pojo.Lesson" %>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%! static Logger logger = Logger.getLogger(dashboard_jsp.class); %>


<%--<% logger.debug("Start open page"); %>--%>
<%@ include file="header.jsp"%>

<div class="container">
    <main class="content">

        <div>

        </div>

        <table class="lesson-head">
            <tr>
                <th>Дата</th>
                <th>Тема</th>
                <%--<th>Посещение</th>--%>
                <%--<th>Оценка</th>--%>
                <%--<th>Домашнее задание</th>--%>
            </tr>
            <% ArrayList<Lesson> lessons = (ArrayList<Lesson>) request.getSession().getAttribute("lesson");%>
            <% for (Lesson l : lessons) { %>
            <%--<th><%l.getLocalDate().toString();%></th>--%>
            <th><%l.getScheduleItem();%></th>
            <th>Посещение</th>
            <th>Оценка</th>
            <th>Домашнее задание</th>
            <% }
                ; %>
        </table>
    </main><!-- .content -->
</div><!-- .container-->

<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
<%--<% logger.debug("End open page"); %>--%>



