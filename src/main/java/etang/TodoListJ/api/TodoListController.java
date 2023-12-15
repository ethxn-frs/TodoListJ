package etang.TodoListJ.api;


import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/todoList")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @GetMapping("getAllTodoLists")
    public List<TodoListDao> getAllTodoLists() {
        List<TodoListDao> todoLists = todoListService.getAllTodoLists();
        return todoLists;
    }

    @GetMapping("/{todoListId}")
    public ResponseEntity<TodoListDao> getTodoListById(@PathVariable Integer todoListId) {
        TodoListDao todoList = todoListService.getTodoListById(todoListId);
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createTodoList(@RequestBody TodoListDao todoListDao) {
        todoListService.createTodoList(todoListDao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{todoListId}/add-item")
    public ResponseEntity<Void> addItemToTodoList(@PathVariable Integer todoListId, @RequestBody ItemDao itemDao) {
        todoListService.addItemToTodoList(todoListId, itemDao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
