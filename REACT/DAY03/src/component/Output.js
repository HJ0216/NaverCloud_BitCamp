import React from 'react';

const Output = (props) => {
    return (
        <div>
            <h2>Output Component</h2>
            <h1>{props.name}님이 좋아하는 과일: {props.fruit}</h1>
        </div>
    );
};
/*
const Output = (props) => {
    const {name, fruit} = props
    return (
        <div>
            <h2>Output Component</h2>
            <h1>{name}님이 좋아하는 과일: {fruit}</h1>
        </div>
    );
};
*/
export default Output;