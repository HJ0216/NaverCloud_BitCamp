import React from 'react';

const Name = ({name, onInputName}) => {
    return (
        <div>
            <h2>Name Component</h2>
            <label>Enter the Name</label>
            <input type='text' value={name} onChange={onInputName} />
        </div>
    );
};

export default Name;