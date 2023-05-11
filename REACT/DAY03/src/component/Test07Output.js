import React from 'react';

const Test07Output = (props) => {
    const {name, age, addr, phone} = props;
    return (
        <div>
            <h3>{name}님</h3>
            <h3>설문조사 감사드립니다.</h3>
        </div>
    );
};

export default Test07Output;