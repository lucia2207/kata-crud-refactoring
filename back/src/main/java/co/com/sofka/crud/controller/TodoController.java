package co.com.sofka.crud.controller;

import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.service.TodoService;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping(value = "/todos") //devuelve allToDos
    public ResponseEntity<List<Todo>> list(){
        List<Todo> todos = new ArrayList<Todo>();
        service.list().forEach(todos::add);

        if (todos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping(value = "/todo/{groupListId}") //crear tarea
    public ResponseEntity<Todo> save(@RequestBody Todo todo, @PathVariable Long groupListId){
        if (todo.getName().trim().isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            service.save(todo, groupListId);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "/todo")// actualizar tarea
    public ResponseEntity<Todo> update(@RequestBody Todo todo){
        if (todo.getName().trim().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        if(todo.getId() != null){
            Todo actualizar;
            try {
                actualizar = service.get(todo.getId());
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            try {
                Todo nuevo = service.save(todo, actualizar.getGroupListId());
                return new ResponseEntity<>(nuevo, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping(value = "/todo/{id}")//borrar tarea
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        try {
            Todo aborrar = service.get(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/todo/{id}")//obtener 1
    public ResponseEntity<Todo> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.get(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/todos/{groupid}") //obtener por grupos
    public ResponseEntity<List<Todo>> listByGroupListId(@PathVariable("groupid")Long groupid) {
        List<Todo> todos = new ArrayList<Todo>();
        service.getByGroupListId(groupid).forEach(todos::add);

        if (todos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}
