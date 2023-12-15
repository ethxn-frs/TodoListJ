package etang.TodoListJ.service.impl;

import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.persistence.entity.Item;
import etang.TodoListJ.persistence.entity.TodoList;
import etang.TodoListJ.persistence.repository.BasicRepositoryTodoList;
import etang.TodoListJ.service.ItemService;
import etang.TodoListJ.service.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private BasicRepositoryTodoList repo;

    @Override
    public List<TodoListDao> getAllTodoLists(){

        List<TodoList> todoLists = (List<TodoList>) repo.findAll();

        List<TodoListDao> res = new ArrayList<>();

        for (TodoList t : todoLists){

            TodoListDao tDao = new TodoListDao();
            tDao.setId(t.getId());
            tDao.setName(t.getName());
            tDao.setItems(t.getItems());

            res.add(tDao);
        }

        return res;
    }

    @Override
    public TodoListDao getTodoListById(Integer id){

        TodoListDao tDao = new TodoListDao();

        if (repo.existsById(id)){
            TodoList t = repo.findById(id).get();
            tDao.setId(t.getId());
            tDao.setName(t.getName());
            tDao.setItems(t.getItems());
        }
        return tDao;
    }

    @Override
    public void  createTodoList(TodoListDao tDao) {

        TodoList todoList = new TodoList();
        todoList.setName(tDao.getName());
        repo.save(todoList);
    }

    @Override
    public void addItemToTodoList(Integer todoListId, ItemDao iDao) {
        TodoList todoList = repo.findById(todoListId).get();

        if ( todoList.getItems().size() >= 10){
            System.out.println("La liste de l'utilisateur est déja pleine");
            return;
        }
        Item item = new Item();
        item.setId(iDao.getId());
        item.setContent(iDao.getContent());
        item.setCreationDate(iDao.getCreationDate());
        item.setTodoList(todoList);

        todoList.getItems().add(item);
        repo.save(todoList);
    }
}
