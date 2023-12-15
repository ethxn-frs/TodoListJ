package etang.TodoListJ.ServiceTest.ImplTest;

import etang.TodoListJ.TodoListJApplication;
import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.persistence.entity.Item;
import etang.TodoListJ.persistence.entity.TodoList;
import etang.TodoListJ.persistence.repository.BasicRepositoryTodoList;
import etang.TodoListJ.service.impl.TodoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TodoListJApplication.class)
@ExtendWith(SpringExtension.class)
class TodoListServiceImplTest {

    @Mock
    private BasicRepositoryTodoList repo;

    @InjectMocks
    private TodoListServiceImpl todoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddItemToTodoList() {
        // Given
        int todoListId = 1;
        TodoList todoList = new TodoList();
        todoList.setId(todoListId);
        todoList.setItems(new ArrayList<>());

        ItemDao itemDao = new ItemDao();
        itemDao.setId(1);
        itemDao.setContent("Test Item");
        itemDao.setCreationDate(LocalDateTime.now());

        when(repo.findById(todoListId)).thenReturn(Optional.of(todoList));
        when(repo.save(todoList)).thenReturn(todoList);

        // When
        todoListService.addItemToTodoList(todoListId, itemDao);

        // Then
        verify(repo, times(1)).findById(todoListId);
        verify(repo, times(1)).save(todoList);

        assertEquals(1, todoList.getItems().size());

        Item addedItem = todoList.getItems().get(0);
        assertEquals(itemDao.getId(), addedItem.getId());
        assertEquals(itemDao.getContent(), addedItem.getContent());
        assertEquals(itemDao.getCreationDate(), addedItem.getCreationDate());
        assertEquals(todoList, addedItem.getTodoList());
    }

    @Test
    void testAddItemToTodoListWithFullList() {
        // Given
        int todoListId = 1;
        TodoList todoList = new TodoList();
        todoList.setId(todoListId);

        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setId(i + 1);
            item.setContent("Test Item " + (i + 1));
            item.setCreationDate(LocalDateTime.now());
            item.setTodoList(todoList);
            itemList.add(item);
        }

        todoList.setItems(itemList);

        ItemDao itemDao = new ItemDao();
        itemDao.setId(11);
        itemDao.setContent("Test Item 11");
        itemDao.setCreationDate(LocalDateTime.now());

        when(repo.findById(todoListId)).thenReturn(Optional.of(todoList));

        todoListService.addItemToTodoList(todoListId, itemDao);

        // Then
        verify(repo, times(1)).findById(todoListId);

        assertEquals(10, todoList.getItems().size());
    }

}
