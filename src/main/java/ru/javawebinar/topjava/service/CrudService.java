package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by shuto on 15.07.2017.
 */
public interface CrudService {

    List<MealWithExceed> getMeals(LocalTime startTime, LocalTime endTime);
}
