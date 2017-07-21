package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> this.save(m, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else {
            Meal mealFromRepo = repository.get(meal.getId());
            if (mealFromRepo != null && mealFromRepo.getUserId() != userId) {
                return null;
            }
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int mealId, int userId) {
        Meal meal = repository.get(mealId);
        return (meal != null && meal.getUserId() == userId && repository.remove(mealId) != null);
    }

    @Override
    public Meal get(int mealId, int userId) {
        Meal meal = repository.get(mealId);
        return (meal != null && meal.getUserId() == userId) ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = repository.values().stream()
                .filter(m -> m.getUserId() == userId)
                .collect(Collectors.toList());
        meals.sort(Comparator.comparing(Meal::getDateTime).reversed());
        return meals;
    }
}

