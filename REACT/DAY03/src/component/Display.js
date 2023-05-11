import React from 'react';

const Display = (props) => {
// const Display = ({name, onInputName}) => {
    // {name, onInputName} 대신 props 가능
    // props: 객체이므로 {} 객체화 필요 X
    // name, onInputName: 변수 -> {} 객체로 감싼 후 사용 가능
    return (
        <div>
            <h2>Display Component</h2>
            <h1>I like {props.name}</h1>
        </div>
    );
};

export default Display;