<%--
  Created by IntelliJ IDEA.
  User: shuto
  Date: 24.09.2017
  Time: 5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a href="#" data-toggle="dropdown" class="dropdown-toggle">
    ${pageContext.response.locale}
    <b class="caret"></b>
</a>
<ul class="dropdown-menu">
    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
</ul>
