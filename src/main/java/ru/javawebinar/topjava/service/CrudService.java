package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CrudService {

    List<MealWithExceed> getMeals(LocalTime startTime, LocalTime endTime);

    void deleteMeal(int id);

    void addMeal(LocalDateTime dateTime, String description, int calories);

    void editMeal(int id, LocalDateTime dateTime, String description, int calories);
}
