import React, { useEffect, useState } from 'react';

const Test02Sub = () => {
    const [x, setX] = useState(0);
    const [y, setY] = useState(0);

    const onMove = (e) => {
        setX(e.clientX);
        setY(e.clientY);
        // clientX, clientY: 사용자가 마우스를 클릭하거나 움직일 때의 좌표를 나타내는 값
        // React에서 이벤트 핸들러로 전달된 인자 e에서 사용할 수 있음
    }

    useEffect(() => {
        console.log('useEffect');
        window.addEventListener('mousemove', onMove);
        // 브라우저 창(window)에서 마우스 커서를 움직일 때(mousemove)마다 onMove 함수가 호출되도록 이벤트를 등록하는 구문
        // 1. 이벤트 등록 후 마우스 커서를 움직이면 onMove 함수가 호출
        // 2. 호출된 함수에서 e.clientX와 e.clientY를 통해 마우스 커서 위치 정보를 가져옴
        // 3. setX와 setY 함수에 2번 값을 주입하여 x와 y 상태를 갱신

        // useEffect에서 return 함수를 사용하면 cleanup 함수가 호출됨
        // cleanup 함수는 보통 리소스 해제 등의 작업을 수행
        // useEffect가 마운트 해제될 때 cleanup 함수가 호출되는 것은 마운트 단계에서 수행한 작업을 해제하기 위한 용도로 사용
        // 불필요한 메모리 사용과 성능 저하를 방지하기 위함
        return () => {
            console.log('cleanup');
            window.removeEventListener('mousemove', onMove);

        }
    }, [])
    // Test02Sub 컴포넌트가 처음으로 생성될 때(=마운트될 때), 단 1번만 실행됨

    return (
        <div>
            <h2>마우스 좌표</h2>
            <div style={{border: '1px solid #000', width: 400, padding: 30, margin: 15}}>
                <h3>x축: {x}, y축: {y}</h3>
            </div>
        </div>
    );
};

export default Test02Sub;