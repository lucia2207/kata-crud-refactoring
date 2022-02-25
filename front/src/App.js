import React from 'react';
import FormToDo from './components/todos/FormToDo';
import ListToDo from './components/todos/ListToDo';
import StoreProvider from './components/todos/provider';

function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <FormToDo />
    <ListToDo />
  </StoreProvider>
}
export default App;
