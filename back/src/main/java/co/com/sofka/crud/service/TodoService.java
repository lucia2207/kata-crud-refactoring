package co.com.sofka.crud.service;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.model.TodoDTO;
import co.com.sofka.crud.repository.GroupListRepo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private GroupListRepo repoGroupList;

    public Iterable<TodoDTO> list(){
        List<TodoDTO> todos = new ArrayList<TodoDTO>();
        repository.findAll().forEach((todo) -> {
            TodoDTO dto = new TodoDTO();

            dto.setId(todo.getId());
            dto.setName(todo.getName());
            dto.setCompleted(todo.isCompleted());
            dto.setGroupListId(todo.getGroupListId());

            todos.add(dto);
        });
        return todos;
    }

    public TodoDTO save(TodoDTO todo, Long groupListId){
        GroupList grupo = repoGroupList.findById(groupListId).orElseThrow();
        todo.setGroupListId(groupListId);


        Todo nuevo = new Todo();
        nuevo.setName(todo.getName());
        nuevo.setCompleted(todo.isCompleted());
        nuevo.setGroupListId(groupListId);

        if (todo.getId() != null)
        {
            nuevo.setId(todo.getId());
        }

        Todo guardado = repository.save(nuevo);
        todo.setId(guardado.getId());

        return todo;
    }

    public void delete(Long id){
        Todo todo = repository.findById(id).orElseThrow();
        repository.delete(todo);
    }

    public TodoDTO get(Long id){
        Todo todo = repository.findById(id).orElseThrow();
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setName(todo.getName());
        dto.setCompleted(todo.isCompleted());
        dto.setGroupListId(todo.getGroupListId());

        return dto;
    }

    public Iterable<TodoDTO> getByGroupListId(Long id) {
        List<TodoDTO> todos = new ArrayList<TodoDTO>();
        repository.findByGroupListId(id).forEach((todo) -> {
            TodoDTO dto = new TodoDTO();
            dto.setId(todo.getId());
            dto.setName(todo.getName());
            dto.setCompleted(todo.isCompleted());
            dto.setGroupListId(todo.getGroupListId());

            todos.add(dto);
        });

        return todos;
    }
}
