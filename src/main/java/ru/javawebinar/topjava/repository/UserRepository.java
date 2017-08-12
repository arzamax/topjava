package ru.javawebinar.topjava.repository;

import org.springframework.context.annotation.Profile;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    @Profile(Profiles.DATAJPA)
    default User getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }
}