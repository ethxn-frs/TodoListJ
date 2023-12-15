package etang.TodoListJ.service;

import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.persistence.entity.Item;

import java.util.List;

public interface TodoListService {
    List<TodoListDao> getAllTodoLists();

    TodoListDao getTodoListById(Integer id);

    void  createTodoList(TodoListDao tDao);

    void addItemToTodoList(Integer todoListId, ItemDao item);
}
