import React from 'react';

const Test01 = () => {

    const test1 = () => {
        alert('test1');
    }
    /*
        function test1(){
            alert('test1');
        }
    */

    const test2 = () => {
        alert('test2');
    }

    const test3 = (num) => {
        alert('num: ' + num);
    }

    const test4 = (num) => {
        alert(`num: ${num}`);
    }

    return (
        <div>
            <h2>이벤트: on + 첫글자 대문자</h2>
            <p>
                {/* <button onClick={test1()}>Click</button> */}
                <button onClick={test1}>Click</button>
                {/* () 추가 시, load되자마자 alert 창 발생 > 함수명을 변수명처럼 기재 */}
                <button onClick={test2}>Click</button>
                {/* <button onClick={test3(10)}>Click</button> */}
                {/* load되자마자 alert 창 발생 > 화살표 함수 사용 */}
                <button onClick={()=>test3(10)}>Click</button>
                <button onClick={()=>test4(20)}>Click</button>
            </p>

            <p>
                <button onClick={()=>{
                    console.log('사자');
                    console.log('기린');
                    console.log('코끼리');
                }}>Click</button>
                <button onClick={() => console.log('빨간 사과')}>Click</button>
            </p>
        </div>
    );
};

export default Test01;



/*
리엑트
: 순수 자바스크립트가 아닌 자바스크립트 기반의 언어
: 함수 뒤에 () 기재 시, 새로고침 하자마자 무조건 실행
> 변수 선언 방식 사용 또는 화살표 함수 방식 사용
*/