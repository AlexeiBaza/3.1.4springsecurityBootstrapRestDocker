package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository - некий интерфейс который с помощью Reflection API способен генерировать запросы
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
//    List<User> findAll();
}