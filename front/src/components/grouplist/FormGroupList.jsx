import React, { useContext, useRef, useState } from 'react';
import { Store, HOST_API } from '../../provider' ;

const FormGroupList = () => {
    const { dispatch, state: { groups } } = useContext(Store);
    const group = groups.item;
    const [state, setState] = useState(group);
    
    const formRef = useRef(null);

    const onAdd = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: null
        };

        fetch(HOST_API + "/group", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then((group) => {
                dispatch({ type: "add-group-item", item: group });
                setState({ name: "" });
                formRef.current.reset();
            });
    }

    const onEdit = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: group.id
        };

        fetch(HOST_API + "/group", {
            method: "PUT",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then((group) => {
                dispatch({ type: "update-group-item", item: group });
                setState({ name: "" });
                formRef.current.reset();
            });
    }

    return <form ref={formRef}>
        <input
            type="text"
            name="name"
            placeholder="Crear una lista de tareas"
            defaultValue={group.name}
            onChange={(event) => {
                setState({...state, name: event.target.value})
            }} />
        {group.id && <button onClick={onEdit}>Actualizar Lista</button>}
        {!group.id && <button onClick={onAdd}>Crear Lista</button>}
    </form>
}

export default FormGroupList;