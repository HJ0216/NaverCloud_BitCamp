import React from 'react';

const Test03Modal = ({onClose}) => {
    // 선언과 사용되는 곳이 다르므로 객체 선언 시, 변수처럼 함수도 전달한 것을 받음
    return (
        <>
            <div className='bg'></div>
            <div className='popup'>
                <p className='closex' style={{cursor: 'pointer'}} onClick={onClose}>X</p>
                <h2>Have a nice day</h2>
            </div>
        </>
    );
};

export default Test03Modal;