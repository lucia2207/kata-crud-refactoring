import React from 'react';
import StoreProvider from './provider';
import ListGroupList from './components/grouplist/ListGroupList';
import 'bootstrap/dist/css/bootstrap.min.css' ;

function App() {
  
  return <StoreProvider>
    <ListGroupList />
  </StoreProvider>
  
}
export default App;
