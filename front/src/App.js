import React from 'react';
import StoreProvider from './provider';
import ListGroupList from './components/grouplist/ListGroupList';

function App() {
  return <StoreProvider>
    <ListGroupList />
  </StoreProvider>
}
export default App;
