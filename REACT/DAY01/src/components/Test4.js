import React from 'react';
import Cat from './Cat';
import Lion from './Lion';
import Person from './Person';

const Test4 = () => {
    return (
        <div>
            <Cat name='고양이' />
            <Lion name='사자' />
            <Person name='홍길동' age='25' />
        </div>
    );
};

export default Test4;