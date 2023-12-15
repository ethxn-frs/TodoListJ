package etang.TodoListJ.service.impl;

import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.dao.UserDao;
import etang.TodoListJ.persistence.entity.TodoList;
import etang.TodoListJ.persistence.entity.User;
import etang.TodoListJ.persistence.repository.BasicRepositoryUser;
import etang.TodoListJ.service.TodoListService;
import etang.TodoListJ.service.UserService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private BasicRepositoryUser repo;

    @Autowired
    private TodoListService todoListService;

    @Override
    public List<UserDao> findAll() {
        List<UserDao> res = new ArrayList<>();
        List<User> userList = (List<User>) repo.findAll();

        for (User user : userList){
            UserDao uDao = new UserDao();
            uDao.setId(user.getId());
            uDao.setEmail(user.getEmail());
            uDao.setPassword(user.getPassword());
            uDao.setFName(user.getFName());
            uDao.setLName(user.getLName());
            uDao.setBDate(user.getBDate());
            uDao.setTodoList(user.getTodoList());
            res.add(uDao);
        }
        return res;
    }

    @Override
    public UserDao getUserById(Integer id){

        User user = repo.findById(id).get();

        UserDao uDao = new UserDao();
        uDao.setId(user.getId());
        uDao.setEmail(user.getEmail());
        uDao.setPassword(user.getPassword());
        uDao.setFName(user.getFName());
        uDao.setLName(user.getLName());
        uDao.setBDate(user.getBDate());
        uDao.setTodoList(user.getTodoList());

        return uDao;
    }

    @Override
    public void deleteUser(Integer id) {
        if( repo.existsById(id)){
            repo.deleteById(id);
        }
    }

    @Transactional
    @Override
    public void addTodoListToUser(Integer userId, TodoListDao todoListDao) {

        if (repo.findById(userId).isPresent()) {
            User user = repo.findById(userId).get();
            if (user.getTodoList() == null) {
                TodoList todoList = new TodoList();
                todoList.setId(todoListDao.getId());
                todoList.setName(todoListDao.getName());
                todoList.setUser(user);

                user.setTodoList(todoList);
                repo.save(user);
            } else {
                throw new IllegalStateException("L'utilisateur a déjà une TodoList.");
            }
        } else {
            throw new NoSuchElementException("Utilisateur non trouvé avec l'ID : " + userId);
        }
    }

    @Override
    public void createUser(UserDao uDao) {
        User user = new User();
        user.setEmail(uDao.getEmail());
        user.setPassword(uDao.getPassword());
        user.setFName(uDao.getFName());
        user.setLName(uDao.getLName());
        user.setBDate(uDao.getBDate());

        repo.save(user);
    }

    @Transactional
    @Override
    public TodoListDao getTodoListByUserId(Integer userId) {

        User user = new User();
        TodoListDao tDao = new TodoListDao();
        if (repo.existsById(userId)){

            user = repo.findById(userId).get();
            TodoList todoList = user.getTodoList();
            tDao.setId(todoList.getId());
            tDao.setName(todoList.getName());
            tDao.setItems(todoList.getItems());
        }
        return tDao;
    }
}
