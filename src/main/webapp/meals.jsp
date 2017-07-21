<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }

        .inline-block {
            display: inline-block;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list</h2>
    <div>
        <form action="meals" method="get">
            <input type="hidden" name="action" value="filter">
            <div class="inline-block">
                <div>
                    <label for="startDate">От даты:</label>
                    <input id="startDate" type="date" name="startDate" value="${param.startDate}">
                </div>
                <div>
                    <label for="endDate">До даты:</label>
                    <input id="endDate" type="date" name="endDate" value="${param.endDate}">
                </div>
            </div>
            <div class="inline-block">
                <div>
                    <label for="startTime">От времени:</label>
                    <input id="startTime" type="time" name="startTime" value="${param.startTime}">
                </div>
                <div>
                    <label for="endTime">До времени:</label>
                    <input id="endTime" type="time" name="endTime" value="${param.endTime}">
                </div>
            </div>
            <div>
                <input type="submit" value="Фильтровать">
            </div>
        </form>
        <form action="meals" method="get">
            <input type="submit" value="Очистить фильтр">
        </form>
    </div>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>