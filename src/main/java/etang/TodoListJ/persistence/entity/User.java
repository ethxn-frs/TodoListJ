package etang.TodoListJ.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String fName;

    @Column(name = "lastName")
    private String lName;

    @Column(name = "birthDate")
    private LocalDateTime bDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_list_id", referencedColumnName = "id")
    private TodoList todoList;
}
