package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(service.get(MEAL_ID, USER_ID), MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ID, ANOTHER_USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), MEALS.subList(1, MEALS.size()));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL_ID, ANOTHER_USER_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(
                service.getBetweenDates(MEALS.get(0).getDate(), MEALS.get(2).getDate(), USER_ID),
                MEALS.subList(0, 3));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(
                service.getBetweenDateTimes(MEALS.get(4).getDateTime(), MEALS.get(3).getDateTime(), USER_ID),
                MEALS.subList(3, 5));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), MEALS);
    }

    @Test
    public void testUpdate() throws Exception {
        MATCHER.assertEquals(service.update(UPDATED_MEAL, USER_ID), UPDATED_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        service.update(UPDATED_MEAL, ANOTHER_USER_ID);
    }


    @Test
    public void testSave() throws Exception {
        MATCHER.assertEquals(service.save(
                new Meal(SAVED_MEAL.getDateTime(), SAVED_MEAL.getDescription(), SAVED_MEAL.getCalories()), USER_ID),
                SAVED_MEAL);
    }

}