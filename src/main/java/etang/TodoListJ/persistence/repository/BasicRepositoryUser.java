package etang.TodoListJ.persistence.repository;

import etang.TodoListJ.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepositoryUser extends CrudRepository<User, Integer> {
}
