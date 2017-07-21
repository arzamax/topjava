package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final static String EXCEPTION_FORMAT = "Meal with mealId=%d not found for userId=%d";

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        if (!repository.delete(mealId, userId)) {
            throw new NotFoundException(String.format(EXCEPTION_FORMAT, mealId, userId));
        }
    }

    @Override
    public Meal get(int mealId, int userId) throws NotFoundException {
        Meal meal = repository.get(mealId, userId);
        if (meal == null) {
            throw new NotFoundException(String.format(EXCEPTION_FORMAT, mealId, userId));
        }
        return meal;
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException {
        Meal savedMeal = repository.save(meal, userId);
        if (savedMeal == null) {
            throw new NotFoundException(String.format(EXCEPTION_FORMAT, meal.getId(), userId));
        }
        return savedMeal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> getFiltered(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return repository.getFiltered(userId, startDate, endDate, startTime, endTime);
    }
}