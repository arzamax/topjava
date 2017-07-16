package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CrudServiceImpl implements CrudService {
    private static final int CALORIES_PER_DAY = 2000;

    private AtomicInteger idCounter;
    private Map<Integer, Meal> meals;

    public CrudServiceImpl() {
        meals = new ConcurrentHashMap<>();
        idCounter = new AtomicInteger();
    }

    @Override
    public void initTestData() {
        meals.put(0, new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        idCounter.set(6);
    }

    @Override
    public List<MealWithExceed> getMeals() {
        return MealsUtil.getFilteredWithExceeded(meals.values(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }

    @Override
    public Meal getMeal(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(id);
    }

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        int id = idCounter.getAndIncrement();
        meals.put(id, new Meal(id, dateTime, description, calories));
    }

    @Override
    public void editMeal(int id, LocalDateTime dateTime, String description, int calories) {
        meals.put(id, new Meal(id, dateTime, description, calories));
    }
}
