package ru.javawebinar.topjava.service;


import org.springframework.context.annotation.Profile;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void evictCache();

    List<User> getAll();

    @Profile(Profiles.DATAJPA)
    User getWithMeals(int id) throws NotFoundException;
}