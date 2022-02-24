import React, { useReducer, createContext } from 'react';
import reducer from './reducer';

const HOST_API = "http://localhost:8080/api";

const Store = createContext();


const StoreProvider = ({ children }) => {

    const initialState = {
        todo: { list: [], item: {} }
      };

    const [state, dispatch] = useReducer(reducer, initialState);
  
    return <Store.Provider value={{ state, dispatch }}>
      {children}
    </Store.Provider>
  
  }
  export default StoreProvider;
  export { Store };