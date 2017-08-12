package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.Comparator;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void testGetWithUser() throws Exception {
        User actual = service.getWithMeals(USER_ID);
        MATCHER.assertEquals(USER, actual);
        actual.getMeals().sort(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()));
        MealTestData.MATCHER.assertCollectionEquals(MEALS, actual.getMeals());
    }
}
