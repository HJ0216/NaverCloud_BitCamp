import React from 'react';

const Animal = ({name, onInputName}) => {
    
    return (
        <div>
            <h2>Animal Component</h2>
            <label>Enter the Animal Name</label>
            <input type='text' value={name} onChange={onInputName} />
        </div>
    );
};

export default Animal;