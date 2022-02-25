import React from 'react';
import GroupList from './GroupList';
import FormGroupList from './FormGroupList';

const ListGroupList = () => {
    const grouplistList = [
        { id: 1, name: "lista de prueba"}
    ]

    return <div>
        <FormGroupList />
        {grouplistList.map(lista => {
            return <GroupList id={lista.id} name={lista.name} />
        })}
    </div>
}

export default ListGroupList;