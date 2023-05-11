import React from 'react';

const Fruit = ({fruit, onInputFruit}) => {
    return (
        <div>
            <h2>Fruit Component</h2>
            <label>Enter the Fruit</label>
            <input type='text' value={fruit} onChange={onInputFruit} />
        </div>
    );
};

export default Fruit;