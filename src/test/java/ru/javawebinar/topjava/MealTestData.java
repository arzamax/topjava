package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ANOTHER_USER_ID = START_SEQ + 1;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100004, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 500),
            new Meal(100003, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
            new Meal(100002, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
            new Meal(100007, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 510),
            new Meal(100006, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500),
            new Meal(100005, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 1000)
    );

    public static final int MEAL_ID = 100004;
    public static final Meal MEAL = MEALS.get(0);

    public static final Meal UPDATED_MEAL =
            new Meal(100004, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Полдник", 600);

    public static final Meal SAVED_MEAL =
            new Meal(100008, LocalDateTime.of(2017, Month.MAY, 31, 20, 0), "Ужин", 1500);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();

}
