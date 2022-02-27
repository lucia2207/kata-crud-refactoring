package co.com.sofka.crud.controller;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.service.GroupListServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class GroupListCont {

    @Autowired
    private GroupListServ service;

    @GetMapping(value = "/group") //devuelve allToDos
    public ResponseEntity<List<GroupList>> list(){
        List<GroupList> groups = new ArrayList<GroupList>();
        service.list().forEach(groups::add);

        if (groups.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping(value = "/group") //crear tarea
    public ResponseEntity<GroupList> save(@RequestBody GroupList grupo){
        if (grupo.getName().trim().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        try {
            service.save(grupo);
            return new ResponseEntity<>(grupo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "/group")// actualizar tarea
    public ResponseEntity<GroupList> update(@RequestBody GroupList grupo){
        if (grupo.getName().trim().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        if(grupo.getId() != null){
            GroupList actualizar;
            try {
                actualizar = service.get(grupo.getId());
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            try {
                GroupList nuevo = service.save(grupo);
                return new ResponseEntity<>(nuevo, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping(value = "/group/{id}")//borrar tarea
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        try {
            GroupList aborrar = service.get(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/group/{id}")//obtener 1
    public ResponseEntity<GroupList> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.get(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
