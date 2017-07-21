package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {

    private static final Logger log = LoggerFactory.getLogger(AuthorizedUser.class);

    private static int id = 1;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        log.info("Authorized user sets to {}", id);
        AuthorizedUser.id = id;
    }

//    public static int id() {
//        return 1;
//    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}