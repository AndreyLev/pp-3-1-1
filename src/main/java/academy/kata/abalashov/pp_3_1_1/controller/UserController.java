package academy.kata.abalashov.pp_3_1_1.controller;

import academy.kata.abalashov.pp_3_1_1.dto.AddUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.AddUserResponse;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserRequest;
import academy.kata.abalashov.pp_3_1_1.dto.UpdateUserResponse;
import academy.kata.abalashov.pp_3_1_1.model.User;
import academy.kata.abalashov.pp_3_1_1.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private final Logger logger;

    private final UserService userService;
    
    public UserController(UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }
    
    @GetMapping("/")
    public String index(Model model)
    {
        logger.info("Пришел запрос на получение всех пользователей");
        
        List<User> users = userService.getAllUsers();
        
        logger.info("Получено {} пользователей", users.size());
        
        model.addAttribute("users", users);
        return "users";
    }
    
    @PostMapping("/add")
    public String addUser(@Valid @RequestBody AddUserRequest request, Model model)
    {
        logger.info("Пришел запрос на сохранение пользователя");
        logger.info("Параметры | {}", request);
        
        AddUserResponse response = userService.addUser(request);
        
        logger.info("ID добавленного пользователя: {}", response.id());
        
        model.addAttribute("user", response);
        return "fragments/userRow :: userRow";
    }
    
    @PostMapping("/remove")
    public ResponseEntity removeUser(@RequestParam @NonNull Long id)
    {
        logger.info("Пришел запрос на удаление пользователя с id = {}", id);
        
        userService.removeUserById(id);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/update")
    public ResponseEntity updateUser(@Valid @RequestBody UpdateUserRequest request)
    {
        logger.info("Пришел запрос на обновление пользователя");
        logger.info("Параметры: {}", request);
        
        UpdateUserResponse response = userService.updateUser(request);
        
        return ResponseEntity.ok(response);
    }
}