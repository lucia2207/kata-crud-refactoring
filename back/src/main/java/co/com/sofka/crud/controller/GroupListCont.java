package co.com.sofka.crud.controller;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.service.GroupListServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class GroupListCont {

    @Autowired
    private GroupListServ service;

    @GetMapping(value = "/group") //devuelve allToDos
    public Iterable<GroupList> list(){
        return service.list();
    }

    @PostMapping(value = "/group") //crear tarea
    public GroupList save(@RequestBody GroupList grupo){
        return service.save(grupo);
    }

    @PutMapping(value = "/group")// actualizar tarea
    public GroupList update(@RequestBody GroupList grupo){
        if(grupo.getId() != null){
            return service.save(grupo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "/group/{id}")//borrar tarea
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "/group/{id}")//obtener 1
    public GroupList get(@PathVariable("id") Long id){
        return service.get(id);
    }

}
