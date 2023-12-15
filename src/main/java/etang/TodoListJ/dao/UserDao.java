package etang.TodoListJ.dao;

import etang.TodoListJ.persistence.entity.TodoList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDao {

    private Integer id;
    private String email;
    private String password;
    private String fName;
    private String lName;
    private LocalDateTime bDate;
    private TodoList todoList;
    private Integer minAge = 13;
}
