<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.log4j.Logger"%>
<%@ page import="ru.innopolis.stc9.servlets.services.builder.BuilderStudent" %>
<%! static Logger logger = Logger.getLogger(schedule_jsp.class); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="header.jsp"%>

<div class="container">
    <main class="content">
        <% logger.debug("Start open page"); %>
        <strong>Расписание</strong>
        <c:if test="${sessionScope.role eq 'STUDENT'}" >
            <c:out value="Расписание группы"/><br>
            <% BuilderStudent student = (BuilderStudent) request.getSession().getAttribute("data");%>
            <% for (int i = 0; i < student.getSchedules().size(); i++) { %>
            <% String s = student.getSchedules().get(i).getDate() + "  " + student.getSchedules().get(i).getTheme();%>
            <%=s%><br>
            <%
                }
                ;
            %>
        </c:if>

        <c:if test="${sessionScope.role eq 'TEACHER'}" >
            <c:out value="Расписание преподавателя"/>
        </c:if>
        <c:if test="${sessionScope.role eq 'ADMIN'}" >
            <c:out value="Выберете расписание:"/>
            <select name="target">
                <option>группы</option>
                <option>преподавателя</option>
            </select>

        </c:if>
        <% logger.debug("End open page"); %>
    </main><!-- .content -->
</div><!-- .container-->

<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
