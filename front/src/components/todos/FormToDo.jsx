import React, { useContext, useRef, useState } from 'react';
import { Store, HOST_API } from '../../provider' ;

const FormToDo = ({gid}) => {
    const formToDoRef = useRef(gid);
    const { dispatch, state: { todo, editTodo } } = useContext(Store);
    // const item = todo.item;
    const item = editTodo.find(elemento => elemento.groupListId === gid) || {};
    const [state, setState] = useState(item);


    const onAdd = (event) => {
      event.preventDefault();
  
      let campo = state.name ;
      let error = {} ;
      let campoValido = true ;
        //verificacion
      if (campo === undefined || campo.trim().length <= 0 ) {
          campoValido = false;
          error = "Error, no puedes ingresar una tarea vacia";
          setState({
            error: error,
            name: campo
        })
      };
      if (campoValido){
        const request = {
          name: state.name,
          id: null,
          completed: false,
          groupListid: gid
        };
    
    
        fetch(HOST_API + "/todo/" + gid, {
          method: "POST",
          body: JSON.stringify(request),
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(response => response.json())
          .then((todo) => {
            dispatch({ type: "add-item", item: todo });
            setState({ name: "" });
            formToDoRef.current.reset();
          });
      }
      
    }
  
    const onEdit = (event) => {
      
      event.preventDefault();
  
      let campo = state.name ;
      let error = {} ;
      let campoValido = true ;
        //verificacion
      if (campo === undefined || campo.trim().length <= 0 ) {
          campoValido = false;
          error = "Error, no puedes ingresar una tarea vacia";
          setState({
            error: error,
            name: campo
        })
      };
      if (campoValido){
        const request = {
          name: state.name,
          id: item.id,
          isCompleted: item.isCompleted,
          groupListid: item.groupListid
        };
    
    
        fetch(HOST_API + "/todo", {
          method: "PUT",
          body: JSON.stringify(request),
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(response => response.json())
          .then((todo) => {
            dispatch({ type: "update-item", item: todo });
            setState({ name: "" });
            formToDoRef.current.reset();
          });
      }
        
    }

    return <form ref={formToDoRef} className="input-group">
      <input
        name="name"
        type="text" className={`form-control ${!!state.error ? 'is-invalid' : '' }`}
        placeholder="¿Qué piensas hacer hoy?"
        defaultValue={item.name}
        onChange={(event) => {
          setState({ ...state, name: event.target.value })
        }}  />
      {item.id && <button onClick={onEdit} className="btn btn-outline-secondary" id="button-addon2">Actualizar</button>}
      {!item.id && <button onClick={onAdd} className="btn btn-outline-secondary" id="button-addon2">Crear</button>}
      {state.error && <div className="invalid-feedback">{state.error}</div>}
    </form>
  }
  export default FormToDo;