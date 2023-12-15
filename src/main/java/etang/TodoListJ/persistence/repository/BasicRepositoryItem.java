package etang.TodoListJ.persistence.repository;

import etang.TodoListJ.persistence.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepositoryItem extends CrudRepository<Item, Integer>{
}
