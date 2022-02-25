package co.com.sofka.crud.service;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.GroupListRepo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupListServ {

    @Autowired
    private GroupListRepo repository;

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<GroupList> list(){
        return repository.findAll();
    }

    public GroupList save(GroupList grupo){
        return repository.save(grupo);
    }

    public void delete(Long id){
        List<Todo> aborrar = (List<Todo>) todoRepository.findByGroupListId(id);

        // si no esta vacio el grupo borrar los todos
        if (!aborrar.isEmpty()){
            aborrar.forEach(todo -> {
                todoRepository.delete(todo);
            });
        }

        repository.delete(get(id));
    }

    public GroupList get(Long id){
        return repository.findById(id).orElseThrow();
    }

}
