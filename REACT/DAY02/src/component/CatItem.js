import React from 'react';

const CatItem = ({item}) => {
    const {id, img, title} = item
    return (
        <li>
            <a href='#'>
                <div><img src={img} /></div>
                <h3>{title}</h3>
            </a>
        </li>
    );
};

export default CatItem;