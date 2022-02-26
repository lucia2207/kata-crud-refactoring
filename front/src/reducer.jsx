function reducer(state, action) {
    switch (action.type) {
        case 'update-item':
            const todoUpItem = state.todo;
            const listUpdateEdit = todoUpItem.list.map((item) => {
                if (item.id === action.item.id) {
                    return action.item;
                }
                return item;
            });
            todoUpItem.list = listUpdateEdit;
            todoUpItem.item = {};
            return { ...state, todo: todoUpItem }

        case 'delete-item':
            const todoUpDelete = state.todo;
            const listUpdate = todoUpDelete.list.filter((item) => {
                return item.id !== action.id;
            });
            todoUpDelete.list = listUpdate;
            return { ...state, todo: todoUpDelete }

        case 'update-list':
            const todoUpList = state.todo;
            todoUpList.list = action.list;
            return { ...state, todo: todoUpList }
        
        case 'edit-item':
            const todoUpEdit = state.todo;
            todoUpEdit.item = action.item;
            return { ...state, todo: todoUpEdit }

        case 'add-item':
            const todoUp = state.todo.list;
            todoUp.push(action.item);
            return { ...state, todo: {list: todoUp, item: {}} }

        case 'update-group-item':
            const groupsUpItem = state.groups;
            const groupsListUpdate = groupsUpItem.list.map((item) => {
                if (item.id === action.item.id) {
                    return action.item;
                }
              return item;
            });

          groupsUpItem.list = groupsListUpdate;
          return { ...state, groups: groupsUpItem }

        case 'delete-group-item':
            const groupsDel = state.groups;
            const todosGroupDel = state.todo;
            const groupsUpdate = groupsDel.list.filter((item) => {
                return item.id !== action.id;
            });
            const todosGroupUpdate = todosGroupDel.list.filter((item) => {
                return item.groupListId !== action.id;
            })

            const newState = { ...state };
            newState.groups.list = groupsUpdate;
            newState.todo.list = todosGroupUpdate;
            newState.todo.item = {};
            return newState;

        case 'update-group-list':
            const groupsUpList = state.groups;
            groupsUpList.list = action.list;
            return { ...state, groups: groupsUpList }

        case 'edit-group-item':
            const groupsEditItem = state.groups;
            groupsEditItem.item = action.item;
            return { ...state, groups: groupsEditItem }

        case 'add-group-item':
            const groupsAdd = state.groups.list;
            groupsAdd.push(action.item);
            return { ...state, groups: { list: groupsAdd, item: {} } }
        
        default:
            return state;
    }
  };

  export default reducer;