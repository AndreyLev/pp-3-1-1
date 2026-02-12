package academy.kata.abalashov.pp_3_1_1.service;

import academy.kata.abalashov.pp_3_1_1.dao.UserDao;
import academy.kata.abalashov.pp_3_1_1.dto.AddUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.AddUserResponse;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserResponse;
import academy.kata.abalashov.pp_3_1_1.exception.UserNotFoundException;
import academy.kata.abalashov.pp_3_1_1.model.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    private final Logger logger;
    private final UserDao userDao;
    
    public UserServiceImpl(UserDao userDao, Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }
    
    @Override
    @Transactional
    public AddUserResponse addUser(AddUserRequest request) {
        User user = new User(request.name(), request.lastName(), request.age());
        userDao.saveUser(user);
        
        logger.info("Пользователь {} сохранен в БД", user);
        
        AddUserResponse response = new AddUserResponse(
            user.getId(),
            user.getName(),
            user.getLastName(),
            user.getAge()
        );
        return response;
    }
    
    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
        
        logger.info("Пользователь с id = {} удален из БД", id);
    }
    
    @Override
    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        Long userId = request.id();
        Optional<User> optionalUser = getUserById(userId);
        User user = optionalUser.orElseThrow(
            () -> new UserNotFoundException(
                "Ошибка обновления: пользователь с id = %d не найден".formatted(userId))
        );
        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setAge(request.age());
        User savedUser = userDao.updateUser(user);
        
        logger.info("Пользователь с id = {} обновлен", userId);
        
        return new UpdateUserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getLastName(),
            savedUser.getAge()
        );
    }
    
}
