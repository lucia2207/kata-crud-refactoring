import React, {useContext} from 'react';
import { Store, HOST_API } from "../../provider";

import FormToDo from '../todos/FormToDo';
import ListToDo from '../todos/ListToDo';

const GroupList = ({group}) => {
    const { dispatch } = useContext(Store);

    const onDeleteGroup = (id) => {
        fetch(HOST_API + "/group/" + id, {
            method: "DELETE"
        }).then((list) => {
            dispatch({ type: "delete-group-item", id })
        })
    }

    const onEditGroup = (group) => {
        dispatch({ type: "edit-group-item", item: group })
    }

    return <div>
        <h3>{group.name} ({group.id})</h3>
        <button onClick={() => onDeleteGroup(group.id)}>Eliminar</button>
        <button onClick={() => onEditGroup(group)}>Editar</button>
        <FormToDo gid={group.id}/>
        <ListToDo gid={group.id}/>
    </div>
}

export default GroupList;