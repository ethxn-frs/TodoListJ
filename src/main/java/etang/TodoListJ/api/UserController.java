package etang.TodoListJ.api;

import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.dao.UserDao;
import etang.TodoListJ.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDao>> getAllUsers() {
        List<UserDao> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDao> getUserById(@PathVariable Integer userId) {
        UserDao uDao = userService.getUserById(userId);
        return new ResponseEntity<>(uDao, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody UserDao userDao) {
        userService.createUser((userDao));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/add-todolist")
    public ResponseEntity<Void> addTodoListToUser(@PathVariable Integer userId, @RequestBody TodoListDao todoListDao) {
        userService.addTodoListToUser(userId, todoListDao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/{userId}/TodoList")
    public TodoListDao getTodoListById(@PathVariable Integer userId){
        TodoListDao tDao = userService.getTodoListByUserId(userId);
        return tDao;
    }
}
