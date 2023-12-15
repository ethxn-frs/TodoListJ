package etang.TodoListJ.api;

import etang.TodoListJ.dao.ItemDao;
import etang.TodoListJ.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDao> getItemById(@PathVariable Integer itemId) {
        ItemDao item = itemService.getItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/createItem")
    public ResponseEntity<Void> createItem(@RequestBody ItemDao itemDto) {
        itemService.createItem(itemDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
