package etang.TodoListJ.service;

import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.persistence.entity.Item;

public interface ItemService {
    void createItem(ItemDao iDao);
    void deleteItem(Integer itemId);

    ItemDao getItemById(Integer itemId);
}
