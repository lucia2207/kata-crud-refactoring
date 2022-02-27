import React, {useContext} from 'react';
import { Store, HOST_API } from "../../provider";

import FormToDo from '../todos/FormToDo';
import ListToDo from '../todos/ListToDo';

const GroupList = ({group, showCRUD}) => {
    const { dispatch } = useContext(Store);

    const onDeleteGroup = (id) => {
        fetch(HOST_API + "/group/" + id, {
            method: "DELETE"
        }).then(() => {
            dispatch({ type: "delete-group-item", id });
        })
    }

    const onEditGroup = (group) => {
        dispatch({ type: "edit-group-item", item: group })
    }

    return <div className="col group-list">
        <h3>{group.name}</h3>
        {showCRUD && <button onClick={() => onDeleteGroup(group.id)} className="btn btn-outline-danger">Eliminar</button>}
        {false && <button onClick={() => onEditGroup(group)} className="btn btn-outline-warning">Editar</button>}
        <FormToDo gid={group.id}/>
        <ListToDo gid={group.id}/>
    </div>
}

export default GroupList;