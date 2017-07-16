package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CrudService {

    void initTestData();

    List<MealWithExceed> getMeals();

    void deleteMeal(int id);

    void addMeal(LocalDateTime dateTime, String description, int calories);

    void editMeal(int id, LocalDateTime dateTime, String description, int calories);

    Meal getMeal(int id);
}
