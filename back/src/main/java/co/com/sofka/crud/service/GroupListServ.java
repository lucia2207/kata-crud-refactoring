package co.com.sofka.crud.service;

import co.com.sofka.crud.model.GroupList;
import co.com.sofka.crud.repository.GroupListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupListServ {

    @Autowired
    private GroupListRepo repository;

    public Iterable<GroupList> list(){
        return repository.findAll();
    }

    public GroupList save(GroupList grupo){
        return repository.save(grupo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public GroupList get(Long id){
        return repository.findById(id).orElseThrow();
    }

}
