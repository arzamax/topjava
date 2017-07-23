package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getFiltered(LocalDate startDate, LocalDate endDate,
                                       LocalTime startTime, LocalTime endTime) {
        log.info("getFiltered: startDate {}, endDate {}, startTime {}, endTime {}", startDate, endDate, startTime, endTime);
        return MealsUtil.getFilteredWithExceeded(service.getFilteredByDate(AuthorizedUser.getId(),
                (startDate == null) ? LocalDate.MIN : startDate, (endDate == null) ? LocalDate.MAX : endDate),
                (startTime == null) ? LocalTime.MIN : startTime, (endTime == null) ? LocalTime.MAX : endTime,
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.getId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int mealId) {
        log.info("get {}", mealId);
        return service.get(mealId, AuthorizedUser.getId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.save(meal, AuthorizedUser.getId());
    }

    public void delete(int mealId) {
        log.info("delete {}", mealId);
        service.delete(mealId, AuthorizedUser.getId());
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal, meal.getId());
        service.update(meal, AuthorizedUser.getId());
    }
}