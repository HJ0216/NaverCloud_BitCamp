import React from 'react';
import style3 from '../css/TodoList.module.css';

const TodoItem = ({item, onDelete}) => {
    const {seq, text} = item;
    return (
        <li className={style3.TodoList}>
            {text} {/* item 객체에 text가 들어가 있음 */}
            <button onClick={() => onDelete(seq)}>삭제</button>
            {/* onDelete를 선언한 곳에 item을 통해 전달받은 seq를 전달 */}
        </li>
    );
};

export default TodoItem;