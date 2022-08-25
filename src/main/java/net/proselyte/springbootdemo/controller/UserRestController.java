package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//логику обработки клиентских запросов на эндпоинты (URI).
@RestController
/* @ResponseBody - значит что возвращаемое значение будет в теле веб-ответа.
@RestController помечен @ResponseBody.
@RestController* можно заменить на @Controller + @ResponseBody
Response and request можно управлеть в GetMapping аннотации. */
@RequestMapping("/api/v1/admin")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)//В REST-контроллерах спринга все POJO объекты, а также коллекции POJO объектов, которые возвращаются в качестве тел ответов, автоматически сериализуются в JSON, если явно не указано иное
    public ResponseEntity<List<User>> readAll() {
        List<User> userList = userService.readAll();
        return userList != null && !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @PostMapping(path = "/users")
    /* @RequestBody т.к отправляем юзера не из формы Model, а используя JSON.
    Поэтому данные придут в теле запроса*/
    public ResponseEntity<?> createUser(@RequestBody User user) {
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

    @GetMapping(path = "/user")
    public ResponseEntity<User> getAuth() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.readById(user.getId()).get(), HttpStatus.OK);
    }
}
