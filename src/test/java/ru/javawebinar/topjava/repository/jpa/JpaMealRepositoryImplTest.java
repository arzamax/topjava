package ru.javawebinar.topjava.repository.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaMealRepositoryImplTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealRepository repository;

    @Test
    public void testCreate() throws Exception {
        Meal created = getCreated();
        Meal actual = repository.save(created, USER_ID);
        Assert.assertEquals((long) actual.getId(), MEAL1_ID + 8);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), repository.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        MATCHER.assertEquals(updated, repository.save(updated, USER_ID));
    }

    @Test
    public void testUpdateWrongUser() throws Exception {
        Assert.assertNull(repository.save(getUpdated(), ADMIN_ID));
    }

    @Test
    public void testDelete() throws Exception {
        Assert.assertTrue(repository.delete(MEAL1_ID, USER_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), repository.getAll(USER_ID));
    }

    @Test
    public void testDeleteWrongUser() throws Exception {
        Assert.assertFalse(repository.delete(MEAL1_ID, ADMIN_ID));
        MATCHER.assertCollectionEquals(MEALS, repository.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = repository.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    @Test
    public void testGetWrongUser() throws Exception {
        Assert.assertNull(repository.get(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS, repository.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                repository.getBetween(
                        LocalDateTime.of(LocalDate.of(2015, Month.MAY, 30), LocalTime.MIN),
                        LocalDateTime.of(LocalDate.of(2015, Month.MAY, 30), LocalTime.MAX), USER_ID));
    }
}