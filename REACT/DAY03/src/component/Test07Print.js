import React from 'react';

const Test07Print = (props) => {
    return (
        <div>
            <p>
                <ul>
                    <li>이름: <em>{props.name}</em></li>
                    <li>나이: <em>{props.age}</em></li>
                    <li>주소: <em>{props.addr}</em></li>
                    <li>핸드폰: <em>{props.phone}</em></li>
                </ul>            
                <button onClick={props.onPrev}>이전</button>&nbsp;
                <button onClick={props.onNext}>다음</button>
            </p>
        </div>
    );
};

export default Test07Print;