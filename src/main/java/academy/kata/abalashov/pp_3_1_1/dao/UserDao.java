package academy.kata.abalashov.pp_3_1_1.dao;

import academy.kata.abalashov.pp_3_1_1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    Optional<User> findById(long id);
    void saveUser(User user);
    void removeUserById(long id);
    User updateUser(User user);
}
