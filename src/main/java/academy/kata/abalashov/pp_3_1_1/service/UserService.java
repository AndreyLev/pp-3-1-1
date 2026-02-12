package academy.kata.abalashov.pp_3_1_1.service;

import academy.kata.abalashov.pp_3_1_1.dto.AddUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.AddUserResponse;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserResponse;
import academy.kata.abalashov.pp_3_1_1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    AddUserResponse addUser(AddUserRequest request);
    void removeUserById(Long id);
    UpdateUserResponse updateUser(UpdateUserRequest request);
}
