package co.com.sofka.crud.controller;

import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.model.TodoDTO;
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
    public ResponseEntity<List<TodoDTO>> list(){
        List<TodoDTO> todos = new ArrayList<TodoDTO>();
        service.list().forEach(todos::add);

        if (todos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping(value = "/todo/{groupListId}") //crear tarea
    public ResponseEntity<TodoDTO> save(@RequestBody TodoDTO todo, @PathVariable Long groupListId){
        if (todo.getName().trim().isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            TodoDTO nuevo = service.save(todo, groupListId);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "/todo")// actualizar tarea
    public ResponseEntity<TodoDTO> update(@RequestBody TodoDTO todo){
        if (todo.getName().trim().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        if(todo.getId() != null){
            TodoDTO actualizar;
            try {
                actualizar = service.get(todo.getId());
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            try {
                TodoDTO nuevo = service.save(todo, actualizar.getGroupListId());
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
            TodoDTO aborrar = service.get(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/todo/{id}")//obtener 1
    public ResponseEntity<TodoDTO> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.get(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/todos/{groupid}") //obtener por grupos
    public ResponseEntity<List<TodoDTO>> listByGroupListId(@PathVariable("groupid")Long groupid) {
        List<TodoDTO> todos = new ArrayList<TodoDTO>();
        service.getByGroupListId(groupid).forEach(todos::add);

        if (todos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}
