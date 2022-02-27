import React, { useReducer, createContext } from 'react';
import reducer from './reducer';

const Store = createContext();
const HOST_API = "http://localhost:8080/api";

const StoreProvider = ({ children }) => {

    const initialState = {
        todo: { list: [], item: {} },
        groups: { list: [], item: {} },
        editTodo: []
    };

    const [state, dispatch] = useReducer(reducer, initialState);
  
    return <Store.Provider value={{ state, dispatch }}>
        {children}
    </Store.Provider>
  
  }
  export default StoreProvider;
  export { Store, HOST_API };