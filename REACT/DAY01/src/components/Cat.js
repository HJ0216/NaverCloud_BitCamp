import React from 'react';

const Cat = (props) => {
    // props: Test4.js의 name 변수
    return (
        <div>
            <h1>나는 {props.name} Component</h1>
        </div>
    );
};

export default Cat;