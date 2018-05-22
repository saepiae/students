<%@ page import="ru.innopolis.stc9.servlets.pojo.Participant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="left-sidebar">
    <div class="logout">
        <% if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("person") != null) {%>
        <% Participant person = (Participant) request.getSession().getAttribute("person");%>
        <div>
            <%=person.getFirstName() + " " + person.getLastName()%>
        </div>
        <a href="${pageContext.request.contextPath}/login?act=out">Выйти</a>
    </div>
    <%
        }
        ;
    %>
</aside><!-- .left-sidebar -->
