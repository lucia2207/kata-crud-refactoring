package co.com.sofka.crud.controller;

import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping(value = "/todos") //devuelve allToDos
    public Iterable<Todo> list(){
        return service.list();
    }

    @PostMapping(value = "/todo") //crear tarea
    public Todo save(@RequestBody Todo todo){
        return service.save(todo);
    }

    @PutMapping(value = "/todo")// actualizar tarea
    public Todo update(@RequestBody Todo todo){
        if(todo.getId() != null){
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "/todo/{id}")//borrar tarea
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "/todo/{id}")//obtener 1
    public Todo get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @GetMapping(value = "/todos/{groupid}") //obtener por grupos
    public Iterable<Todo> listByGroupListId(@PathVariable("groupid")Long groupid) {
        return service.getByGroupListId(groupid);
    }
}
