import React from 'react';

const Person = ({name, age}) => {
    return (
        <div>
            <h3>{name}의 나이는 {age}살 입니다.</h3>
        </div>
    );
};

export default Person;