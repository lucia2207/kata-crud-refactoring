function reducerGroupList(state, action) {
    switch (action.type) {
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
            const listUpdate = groupsDel.list.filter((item) => {
            return item.id !== action.id;
            });
            return { ...state, groups: { list: listUpdate } }

        case 'update-group-list':
            const groupsUpList = state.groups;
            groupsUpList.list = action.groupsList;
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
}