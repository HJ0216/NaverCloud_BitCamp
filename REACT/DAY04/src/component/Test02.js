import React, { useState } from 'react';
import Test02Sub from './Test02Sub';

const Test02 = () => {
    const [isShow, setIsShow] = useState(false);
    const onToggle = () => {
        setIsShow(!isShow); // toggle 처리
    }

    return (
        <div>
            <button onClick={onToggle}>{isShow ? '숨기기' : '보이기'}</button>
            <hr/>
            {
                isShow && <Test02Sub />
                // Test02Sub 컴포넌트가 마운트 되면 useEffect 함수가 실행
                // isShow가 false로 변경되면 Test02Sub 컴포넌트는 언마운트 되어서 useEffect 함수에서 반환한 cleanup 함수가 호출
                // cleanup 함수에서 등록한 이벤트 리스너를 제거하거나, 다른 정리 작업을 수행할 수 있음
            }
        </div>
    );
};

export default Test02;