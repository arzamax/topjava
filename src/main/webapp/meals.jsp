<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/meals.css" rel="stylesheet"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="table">
    <div class="tr">
        <span class="td">Дата/Время</span>
        <span class="td">Описание</span>
        <span class="td">Калории</span>
    </div>
    <c:forEach items="${meals}" var="meal">
        <div class="tr ${meal.isExceed() ? 'red' : 'green'}">
            <span id="Id${meal.getId()}dateTime" class="td wide">${TimeUtil.getDateTimeAsString(meal.getDateTime())}</span>
            <span id="Id${meal.getId()}description" class="td wide">${meal.getDescription()}</span>
            <span id="Id${meal.getId()}calories" class="td wide">${meal.getCalories()}</span>
            <span class="td">
                <a href="meals?action=edit&id=${meal.getId()}">Редактировать</a>
            </span>
            <form action="meals?action=delete" method="post">
                <span class="hidden"><input name="id" type="text" value="${meal.getId()}"></span>
                <span class="td"><input type="submit" value="Удалить"></span>
            </form>
        </div>
    </c:forEach>
    <form class="tr" action="meals?action=add" method="post">
        <span class="td wide"><input type="datetime-local" name="dateTime" placeholder="Дата/Время"></span>
        <span class="td wide"><input type="text" name="description" placeholder="Описание"></span>
        <span class="td wide"><input type="number" name="calories" placeholder="Калории"></span>
        <span class="td"><input type="submit" value="Добавить"></span>
    </form>
</div>
</body>
</html>
