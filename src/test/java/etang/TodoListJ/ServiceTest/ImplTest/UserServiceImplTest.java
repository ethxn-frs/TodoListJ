package etang.TodoListJ.ServiceTest.ImplTest;

import etang.TodoListJ.TodoListJApplication;
import etang.TodoListJ.dao.TodoListDao;
import etang.TodoListJ.persistence.entity.User;
import etang.TodoListJ.persistence.repository.BasicRepositoryUser;
import etang.TodoListJ.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TodoListJApplication.class)
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @Autowired
    private BasicRepositoryUser userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAddTodoListToUser() {

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFName("John");
        user.setLName("Doe");

        User savedUser = userRepository.save(user);

        TodoListDao todoListDao = new TodoListDao();
        todoListDao.setName("My TodoList");

        // Act
        userService.addTodoListToUser(savedUser.getId(), todoListDao);

        // Assert
        User updatedUser = userRepository.findById(savedUser.getId()).orElseThrow();

        assertNotNull(updatedUser.getTodoList());
        assertEquals(todoListDao.getName(), updatedUser.getTodoList().getName());
    }
}