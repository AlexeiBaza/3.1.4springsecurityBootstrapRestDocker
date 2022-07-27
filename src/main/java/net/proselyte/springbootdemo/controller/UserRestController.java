package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
/* @ResponseBody - значит что возвращаемое значение будет в теле веб-ответа.
@RestController помечен@ResponseBody.
@RestController* можно заменить на @Controller + @ResponseBody
Response and request можно управлеть в GetMapping аннотации. */
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)//"MediaType" by default
    public ResponseEntity<List<User>> readAll() {
        List<User> userList = userService.readAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable("id") long id) {
        Optional<User> user = userService.readById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    В теле запроса НЕ должен содержаться id чтобы произвести создание юзера
    @PostMapping(path = "/users")
    /* @RequestBody т.к отправляем юзера не из формы Model, а используя JSON.
    Поэтому данные придут в теле запроса*/
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    В теле запроса должен содержаться id чтобы произвести обновление юзера
    @PutMapping (path = "/users/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (path = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
