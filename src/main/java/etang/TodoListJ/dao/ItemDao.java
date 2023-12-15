package etang.TodoListJ.dao;

import etang.TodoListJ.persistence.entity.TodoList;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDao {


    private Integer id;
    private String content;
    private LocalDateTime creationDate;

}
