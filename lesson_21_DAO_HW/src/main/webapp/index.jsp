<%@ page import="org.apache.log4j.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! static Logger logger = Logger.getLogger(index_jsp.class); %>
<% logger.debug("Start open page"); %>

<%@ include file="header.jsp"%>
    <div class="container">
      <main class="content">
          <a href="${pageContext.request.contextPath}/login">Войти</a><br>
          <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
      </main><!-- .content -->
    </div><!-- .container-->
<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
<% logger.debug("End open page"); %>