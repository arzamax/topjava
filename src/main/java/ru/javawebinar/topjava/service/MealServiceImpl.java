package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final static String EXCEPTION_FORMAT = "mealId=%d, userId=%d";

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        ValidationUtil.checkNew(meal);
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        ValidationUtil.checkNotFound(repository.delete(mealId, userId),
                String.format(EXCEPTION_FORMAT, mealId, userId));
    }

    @Override
    public Meal get(int mealId, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFound(repository.get(mealId, userId),
                String.format(EXCEPTION_FORMAT, mealId, userId));
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFound(repository.save(meal, userId),
                String.format(EXCEPTION_FORMAT, meal.getId(), userId));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getFilteredByDate(userId, startDate, endDate);
    }
}