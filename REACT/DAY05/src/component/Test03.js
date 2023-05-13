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
// useMemo
// 첫 번째 매개변수로 전달된 콜백 함수의 결과값을 캐시,
// 두 번째 매개변수로 전달된 배열의 값이 변경될 때에만 새로운 결과값을 계산
// count1 변경 ▶ useMemo: return 업데이트 ▶ isEven 업데이트

// const isEven = count1 % 2 === 0;
//  단순 대입으로 변수를 초기화하면, isEven 값은 count1의 초기값에 의존
// 이후 count1 값이 변경되어도 isEven 값은 자동으로 변경되지 않음
// 단, useMemo를 사용하여 의존성 배열을 설정하면, 배열에 전달된 값이 변경될 때마다 isEven 값이 자동으로 계산되며 업데이트됨


// useMemo: return 값을 저장
// useEffect: return 값을 저장하지 않으므로 setter 사용


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
