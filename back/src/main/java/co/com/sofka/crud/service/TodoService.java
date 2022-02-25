package co.com.sofka.crud.service;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.GroupListRepo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private GroupListRepo repoGroupList;

    public Iterable<Todo> list(){
        return repository.findAll();
    }

    public Todo save(Todo todo, Long groupListId){
        GroupList grupo = repoGroupList.findById(groupListId).orElseThrow();
        todo.setGroupListId(groupListId);
        return repository.save(todo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }

    public Iterable<Todo> getByGroupListId(Long id) { return repository.findByGroupListId(id); }
}
