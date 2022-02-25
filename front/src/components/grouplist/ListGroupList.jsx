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

    return <div>
        <FormGroupList />
        {grouplistList.map(lista => {
            return <GroupList key={lista.id} group={lista} />
        })}
    </div>
}

export default ListGroupList;