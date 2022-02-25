import React from 'react';

const FormGroupList = () => {
    return <form>
        <input
            type="text"
            name="name"
            placeholder="Crear una lista de tareas" />
        <button disabled>Crear Lista</button>
    </form>
}

export default FormGroupList;