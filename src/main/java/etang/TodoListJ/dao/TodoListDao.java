package etang.TodoListJ.dao;

import etang.TodoListJ.persistence.entity.Item;
import etang.TodoListJ.persistence.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoListDao {

    private Integer id;
    private String name;
    private List<Item> items;
}
