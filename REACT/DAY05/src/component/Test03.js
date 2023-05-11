import React, { useMemo, useState, useEffect } from 'react';

const Test03 = () => {
    const [count1, setCount1] = useState(1);
    const [count2, setCount2] = useState(1);

/*
const isEven = (() => {
    return count1%2===0;
}, [count1]);
// 브라우저에서 count1 값은 바뀌면서 출력되지만
// isEven이 호출되지 않으므로 값이 업데이트되지 않아서 '짝수' 또는 '홀수' 결과에 영향을 미치지 않음
*/




const isEven = useMemo(() => {
        return count1%2 === 0;
    },[count1]);
// useMemo를 통해서 count1이 변경될 때, isEven 함수도 호출
// const isEven = count1 % 2 === 0; 과 차이???????????
// 


    return (
        <div>
            <h2>count: {count1}</h2>
            <button onClick={() => setCount1(count1+1)}>Incresement</button>

            <h2>count: {count2}</h2>
            <button onClick={() => setCount2(count2+1)}>Incresement</button>

            <h3>
                Result: {isEven ? '짝수' : '홀수'}
            </h3>
        </div>
    );
};

export default Test03;

/*
useMemo
- 리랜더링(새로고침), 최적화
- useMemo는 컴포넌트의 성능을 최적화시킬 수 있는 대표적인 react hooks 중 하나이다.

memoization?
- 기존에 수행한 연산의 결괏값을 어딘가(Buffer)에 저장해 두고 동일한 입력이 들어오면 재활용하는 프로그래밍 기법을 말한다. 
*/