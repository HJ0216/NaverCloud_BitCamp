import React, {useState} from 'react';
import Test03Modal from './Test03Modal';
import '../css/Test03.css'

const Test03 = () => {
    const [isOpen, setIsOpen] = useState(false);
    const onOpen = () => {
        setIsOpen(true);
    }
    const onClose = () => {
        setIsOpen(false);
    }
    /*
        close 함수가 쓰이는 곳은 Test03Modal이지만, 선언되어야하는 곳은 상태변수가 선언된 Test03
        선언과 사용되는 곳이 다르므로 객체 선언 시, 변수처럼 함수도 전달
 
        * 상태변수(isOpen)가 선언된 곳에서 함수(onOpen, onClose)도 함께 선언되어야 한다.
    */

    return (
        <div>
            <button className='button' onClick={onOpen}>팝업창</button>
            {
                isOpen && <Test03Modal onClose={onClose}/>
                // 함수가 선언과 사용되는 곳이 다르므로 객체 선언 시, 변수처럼 함수도 전달
            }
        </div>
    );
};

export default Test03;