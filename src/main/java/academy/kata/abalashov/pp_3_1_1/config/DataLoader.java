package academy.kata.abalashov.pp_3_1_1.config;

import academy.kata.abalashov.pp_3_1_1.dao.UserDao;
import academy.kata.abalashov.pp_3_1_1.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {
    
    private UserDao userDao;
    
    public DataLoader(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public void run(String... args) {
        userDao.saveUser(new User("Жанна", "Набатова", (byte) 23));
        userDao.saveUser(new User("Карина", "Нестерова", (byte) 53));
        userDao.saveUser(new User("Павел", "Лягушкин", (byte) 63));
        userDao.saveUser(new User("Алексей", "Заболотный", (byte) 22));
        userDao.saveUser(new User("Вера", "Дробышева", (byte) 34));
        userDao.saveUser(new User("Иван", "Сыромятников", (byte) 70));
        userDao.saveUser(new User("Валентина", "Варфоломеева", (byte) 39));
        userDao.saveUser(new User("Данила", "Никишин", (byte) 84));
    }
}
