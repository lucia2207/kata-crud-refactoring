import React, {useContext , useEffect} from 'react';
import { Store, HOST_API } from "../../provider";

const ListToDo = ({ gid }) => {
    const { dispatch, state: { todo } } = useContext(Store);
    const currentList = todo.list;
  
    useEffect(() => {
      fetch(HOST_API + "/todos")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (id) => {
      fetch(HOST_API + "/todo/" + id, {
        method: "DELETE"
      }).then(() => {
        dispatch({ type: "delete-item", id })
      })
    };
  
    const onEdit = (todo) => {
      dispatch({ type: "edit-item", item: todo })
    };
  
    const onChange = (event, todo) => {
      const request = {
        name: todo.name,
        id: todo.id,
        completed: event.target.checked
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
        });
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div>
      <table key={"table-grouplist-" + gid} className= "table">
        <thead>
          <tr>
            <th>Tarea</th>
            <th>¿Completado?</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {currentList.filter(todo => todo.groupListId === gid).map((todo) => {
            return <tr key={todo.id} style={todo.completed ? decorationDone : {}}>
              <td>{todo.name}</td>
              <td><input key={"checkbox-completed-" + todo.id} className="form-check-input" type="checkbox" defaultChecked={todo.completed} onChange={(event) => onChange(event, todo)}></input></td>
              <td>
                <button key={"button-delete-" + todo.id} className="btn btn-danger" onClick={() => onDelete(todo.id)}>Eliminar</button>
                <button key={"button-edit-" + todo.id} className="btn btn-warning" onClick={() => onEdit(todo)} disabled={todo.completed} >Editar</button>
              </td>
            </tr>
          })}
        </tbody>
      </table>
    </div>
};

export default ListToDo;