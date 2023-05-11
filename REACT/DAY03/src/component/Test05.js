import React, { useState } from 'react';
import Name from './Name';
import Fruit from './Fruit';
import Output from './Output';

const Test05 = () => {
    const [name, setName] = useState('');
    const [fruit, setFruit] = useState('');

    const onInputName = (e) => {
        const {value} = e.target;
        setName(value);
    }

    const onInputFruit = (e) => {
        const {value} = e.target;
        console.log(e.target);
        // 비구조할당
        // e.target.value ▶ value
        setFruit(value);
    }

    return (
        <div>
            <Name name={name} onInputName={onInputName} />
            <br/>
            <Fruit fruit={fruit} onInputFruit={onInputFruit} />
            <br/>
            <Output name={name} fruit={fruit} />
        </div>
    );
};

export default Test05;