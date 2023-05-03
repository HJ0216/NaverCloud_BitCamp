import React from 'react';

/*
{
const Test5Sub = (props) => {
    return (
        <div>
            <h2>신상명세서</h2>
            <ul style={{color: props.color, backgroundColor: props.bgcolor }}>
            stlye 작성 시, {{}} 이중 중괄호 사용
                <li>이름: {props.name}</li>
                <li>나이: {props.age}</li>
                <li>핸드폰: {props.tel}</li>
                <li>주소: {props.addr}</li>
                <li>동의 여부: {props.done === true ? '동의' : '비동의'}</li>
            </ul>
        </div>
    );
};
}
*/


const Test5Sub = ({name, age, addr, tel, color, bgcolor, done}) => {
    return (
        <div>
            <h2>신상명세서</h2>
            <ul style={{color: color, backgroundColor: bgcolor }}>
            
                <li>이름: {name}</li>
                <li>나이: {age}</li>
                <li>핸드폰: {tel}</li>
                <li>주소: {addr}</li>
                <li>동의 여부: {done ? '동의' : '비동의'}</li>
                {/*props.done === false일 경우, 비동의가 아닌 동의가 출력되므로 조건문 자체인 done을 사용 */}
            </ul>
        </div>
    );
};


export default Test5Sub;
