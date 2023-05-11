import React from 'react';

const Test08Item = ({item, onView}) => {

    return (
        // <li onClick={onView(item.id)}> onload 함수가 되므로 화살표 함수 처리
        <li onClick={() => onView(item.id)}>
            <img src={item.img} />
        </li>
    );
};

export default Test08Item;