package ru.javawebinar.topjava.service;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    private static final Logger log = getLogger(MealServiceTest.class);
    private static final Map<String, Long> testDurations = new HashMap<>();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            long duration = TimeUnit.NANOSECONDS.toMillis(nanos);
            log.info("Test method:{}, time={}ms", description.getMethodName(), duration);
            testDurations.put(description.getMethodName(), duration);
        }
    };

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private MealService service;

    static {
        SLF4JBridgeHandler.install();
    }

    @AfterClass
    public static void testsDuration() {
        log.info("------------------------------------------------");
        log.info("-------------------T E S T S--------------------");
        log.info("------------------------------------------------");
        testDurations.forEach((k, v) -> log.info("Method: {} time={}ms", String.format("%1$-20s", k), v));
        log.info("------------------------------------------------");
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        service.delete(MEAL1_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.create(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        service.update(MEAL1, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS, service.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(
                        LocalDate.of(2015, Month.MAY, 30),
                        LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }
}