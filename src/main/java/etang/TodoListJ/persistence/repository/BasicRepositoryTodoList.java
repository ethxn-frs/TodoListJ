package etang.TodoListJ.persistence.repository;

import etang.TodoListJ.persistence.entity.TodoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepositoryTodoList extends CrudRepository<TodoList, Integer> {
}
