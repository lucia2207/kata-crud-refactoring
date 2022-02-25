import React from 'react';

import FormToDo from '../todos/FormToDo';
import ListToDo from '../todos/ListToDo';

const GroupList = ({id, name}) => {
    return <div>
        <h3>{name}</h3>
        <FormToDo />
        <ListToDo />
    </div>
}

export default GroupList;