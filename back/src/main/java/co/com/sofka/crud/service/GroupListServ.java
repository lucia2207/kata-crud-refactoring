package co.com.sofka.crud.service;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.model.GroupListDTO;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.GroupListRepo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupListServ {

    @Autowired
    private GroupListRepo repository;

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<GroupListDTO> list(){
        List<GroupListDTO> groups = new ArrayList<GroupListDTO>();
        repository.findAll().forEach((group) -> {
            GroupListDTO dto = new GroupListDTO();
            dto.setId(group.getId());
            dto.setName(group.getName());
            groups.add(dto);
        });

        return groups;
    }

    public GroupListDTO save(GroupListDTO grupo){
        GroupList nuevo = new GroupList();
        nuevo.setName(grupo.getName());
        GroupList guardado = repository.save(nuevo);
        grupo.setId(guardado.getId());
        return grupo;
    }

    public void delete(Long id){
        List<Todo> aborrar = (List<Todo>) todoRepository.findByGroupListId(id);

        // si no esta vacio el grupo borrar los todos
        if (!aborrar.isEmpty()){
            aborrar.forEach(todo -> {
                todoRepository.delete(todo);
            });
        }

        GroupList group = repository.findById(id).orElseThrow();
        repository.delete(group);
    }

    public GroupListDTO get(Long id){
        GroupList group = repository.findById(id).orElseThrow();
        GroupListDTO dto = new GroupListDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }

}
