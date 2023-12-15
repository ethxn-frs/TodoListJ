package etang.TodoListJ.service;

import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.dao.UserDao;
import etang.TodoListJ.persistence.entity.TodoList;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    List<UserDao> findAll();

    UserDao getUserById(Integer id);

    void deleteUser(Integer id);

    void addTodoListToUser(Integer userId, TodoListDao todoList);

    void createUser(UserDao uDao);

    TodoListDao getTodoListByUserId(Integer userId);
}
