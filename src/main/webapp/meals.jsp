<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/meals.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/meals.js"></script>
    <title>Meals</title>
</head>
<body>
<table>
    <tr>
        <td>Дата/Время</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr id="rowMain${meal.getId()}" class="${meal.isExceed() ? 'red' : 'green'}">
            <td class="wide">${TimeUtil.getDateTimeAsString(meal.getDateTime())}</td>
            <td class="wide">${meal.getDescription()}</td>
            <td class="wide">${meal.getCalories()}</td>
            <td>
                <button onclick="showEditing(${meal.getId()})">Редактировать</button>
            </td>
            <form action="deleteMeal" method="post">
                <td hidden="hidden"><input name="id" type="text" value="${meal.getId()}"></td>
                <td><input type="submit" value="Удалить"></td>
            </form>
        </tr>
        <tr id="rowEdit${meal.getId()}" hidden="hidden">
            <form action="editMeal" method="post">
                <td hidden="hidden"><input type="text" name="id" value="${meal.getId()}"></td>
                <td class="wide"><input type="datetime-local" name="dateTime" value="${meal.getDateTime()}"></td>
                <td class="wide"><input type="text" name="description" value="${meal.getDescription()}"></td>
                <td class="wide"><input type="number" name="calories" value="${meal.getCalories()}"></td>
                <td><input type="submit" value="Сохранить"></td>
            </form>
            <td>
                <button onclick="hideEditing(${meal.getId()})">Отмена</button>
            </td>
        </tr>
    </c:forEach>
    <form action="addMeal" method="post">
        <tr>
            <td class="wide"><input type="datetime-local" name="dateTime" placeholder="Дата/Время"></td>
            <td class="wide"><input type="text" name="description" placeholder="Описание"></td>
            <td class="wide"><input type="number" name="calories" placeholder="Калории"></td>
            <td><input type="submit" value="Добавить"></td>
        </tr>
    </form>
</table>

</body>
</html>
