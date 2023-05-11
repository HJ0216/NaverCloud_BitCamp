import React from 'react';
import style3 from '../css/TodoList.module.css';
import TodoItem from './TodoItem';

const TodoList = ({data, onDelete}) => {
    return (
        <ul className={style3.TodoList}>
            {
                data.map(item => <TodoItem key={item.seq} item={item} onDelete={onDelete} />)
            }
        </ul>
    );
};

export default TodoList;