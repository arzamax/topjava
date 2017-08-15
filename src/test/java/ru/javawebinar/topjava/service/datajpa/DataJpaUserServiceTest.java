package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Comparator;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void testGetWithMeal() throws Exception {
        User actual = service.getWithMeals(USER_ID);
        MATCHER.assertEquals(USER, actual);
        MealTestData.MATCHER.assertCollectionEquals(MEALS, actual.getMeals());
    }

    @Test
    public void testGetWithMealNotExists() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        MATCHER.assertEquals(service.getWithMeals(newUser.getId()), newUser);
    }
}