import React from 'react';

const Lion = (props) => {
    // 비구조할당
    const {name} = props
    return (
        <div>
            <h1>나는 {name} Component</h1>            
        </div>
    );
};

export default Lion;