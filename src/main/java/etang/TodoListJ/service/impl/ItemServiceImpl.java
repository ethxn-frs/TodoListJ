package etang.TodoListJ.service.impl;

import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.persistence.entity.Item;
import etang.TodoListJ.persistence.repository.BasicRepositoryItem;
import etang.TodoListJ.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private BasicRepositoryItem repo;

    @Override
    public void createItem(ItemDao iDao) {
        Item item = new Item();
        item.setContent(iDao.getContent());
        item.setCreationDate(LocalDateTime.now());
        repo.save(item);
    }
    @Override
    public void deleteItem(Integer itemId) {
        if (repo.existsById(itemId)) {
            repo.deleteById(itemId);
        }
    }

    @Override
    public ItemDao getItemById(Integer itemId) {

        ItemDao iDao = new ItemDao();

        if (repo.existsById(itemId)){
            Item i = new Item();
            iDao.setId(i.getId());
            iDao.setContent(i.getContent());
            iDao.setCreationDate(i.getCreationDate());
        }
        return iDao;
    }

}

