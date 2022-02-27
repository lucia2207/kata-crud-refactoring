import React, {useContext , useEffect} from 'react';
import GroupList from './GroupList';
import FormGroupList from './FormGroupList';
import { Store, HOST_API } from "../../provider";

const ListGroupList = () => {
    const { dispatch, state: { groups } } = useContext(Store);
    const grouplistList = groups.list;

    useEffect(() => {
        fetch(HOST_API + "/group")
            .then(response => response.json())
            .then(list => {
                dispatch({ type: "update-group-list", list})
            })
    }, [dispatch]);

    return <div className="container">
        <h1>Dashboard</h1>
        <hr />
        <FormGroupList />
        {grouplistList.map((lista, indice) => {//recorro grouplist y creo comp. 
            return <GroupList key={lista.id} group={lista} showCRUD={indice !== 0} /> ;
        }).reduce((acumulador, componente, indice) => {
            /* creo un array que contendra nuevos arrays con parametros 
            para crear divs con clase row que contengan 2 grouplist cada 1  
            */ 
            if (indice % 2 === 0){ // cuando el indice es par creo el array de parametros  
                acumulador.push([ 'div', { className: 'row', key: 'fila' + indice }, componente ]); 
            } else { // cuando el indice es impar agrego el componente al array anterior
                acumulador[acumulador.length - 1].push( componente );
            }
            return acumulador
        }, []).map(parametros => React.createElement(...parametros))} 
    </div>
}

export default ListGroupList;