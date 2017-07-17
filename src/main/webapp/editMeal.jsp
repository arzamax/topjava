<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/meals.css" rel="stylesheet"/>
    <title>Edit Meal</title>
</head>
<body>
<div>
    <form action="meals?action=edit" method="post" class="table">
        <input class="hidden" type="number" id="id" name="id" placeholder="id" value="${meal.getId()}">
        <div class="tr">
            <span class="td"><label for="dateTime">Дата/Время</label></span>
            <span class="td"><input id="dateTime" type="datetime-local" name="dateTime"
                                    value="${meal.getDateTime()}"></span>
        </div>
        <div class="tr">
            <span class="td"><label for="description">Описание</label></span>
            <span class="td"><input id="description" type="text" name="description"
                                    value="${meal.getDescription()}"></span>
        </div>
        <div class="tr">
            <span class="td"><label for="calories">Калории</label></span>
            <span class="td"><input id="calories" type="number" name="calories" value="${meal.getCalories()}"></span>
        </div>
        <div class="tr">
            <span class="td"><input type="submit" value="Сохранить"></span>
            <span class="td"><button type="reset" onclick="hideEditing()">Отмена</button></span>
        </div>
    </form>
</div>
</body>
</html>
