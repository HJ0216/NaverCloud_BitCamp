import React, { useState } from 'react';

const Test03 = () => {
    /*
        const name = '홍길동';
        const age = 25;
        const color = 'dodgerblue';
    */

    const[name, setName] = useState('홍길동');
    const[age, setAge] = useState(25);
    const[color, setColor] = useState('dodgerblue');

    const onName = () => {
        setName('코난');
    }

    const onAge = (num) => {
        setAge(num);
    }

    const onColor = () => {
        setColor('ghostwhite');
    }

    return (
        <div>
            <h2 style={{background: color}}>
                이름: {name} / 나이: {age}
            </h2>
            <p>
                <button onClick={onName}>코난으로 이름을 변경</button>
                {/* <button onClick={() => onName('코난')}>코난으로 이름을 변경</button> */}
                {/* 메서드() 작성 시, onload로 진행 */}
                <button onClick={() => onAge(30)}>나이를 30으로 변경</button>
                <button onClick={() => setAge(35)}>나이를 35로 변경</button>
                <button onClick={() => onColor()}>배경색을 흰색으로 변경</button>
            </p>
        </div>
    );
};

export default Test03;



/*

useState
- 값이 유동으로 변할 때
- const [상태데이터, 상태데이터의 값을 변경해주는 함수] = React.useState(초기값);

*/