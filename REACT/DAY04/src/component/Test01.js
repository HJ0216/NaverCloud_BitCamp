import React, { useEffect, useState } from 'react';

const Test01 = () => {
    const names = '연예인1, 연예인2, 연예인3, 연예인4, 연예인5, 연예인6, 연예인7, 연예인8, 연예인9, 연예인10, 연예인11'.split(',');
    const [name, setName] = useState('하이');
    const [age, setAge] = useState(30);

    const onName = () => {
        const index = Math.floor(Math.random() * names.length); // 0 <= names < 11
        setName(names[index]);
    }
    const onAge = () => {
        setAge(age + 1);
    }
    // onAge 호출은 setAge 시, age가 최신값으로 저장됨(= 이전 상태값(prevState)을 참조하여 업데이트가 됨)
    // 단순히 setAge의 경우, age가 최신값으로 저장되지 않음(= 각각의 setAge가 이전의 age값을 참조하고 있음)


    // name 값이 바뀔 때만 수행
    useEffect(() => {
        console.log('안녕하세요!!!');
        // 컴포넌트가 화면에 나타날 때 문제 여부를 파악하기 위해서 두 번 실행함
    }, [name])


    return (
        <div>
            <button onClick={onName}>이름 변경</button>
            <button onClick={onAge}>나이 증가</button>
            <hr/>
            <h1>{name}</h1>
            <h1>{age}</h1>
        </div>
    );
};

export default Test01;

/*
useEffect
* 렌더링 혹은 변수의 값 혹은 오브젝트가 달라지게 되면, 그것을 인지하고 업데이트를 해주는 함수
cf. 렌더링과 마운트
 - 렌더링은 컴포넌트가 화면에 그려질 때마다 발생하는 것을 의미하며, 상태나 속성 등이 변경될 때 발생합니다.
즉, 화면이 업데이트될 때마다 렌더링이 실행됩니다.
 - 마운트는 컴포넌트가 처음으로 생성될 때 실행됩니다.
이 과정에서는 컴포넌트의 초기화 작업이 수행됩니다.
예를 들어, 컴포넌트의 상태(State)나 속성(Props) 값이 설정되고, 필요한 리소스를 가져와서 초기화하는 작업 등이 이루어집니다.
이후에는 마운트가 다시 발생하지 않습니다.
(함수 호출과 유사한 개념)

useEffect 형식
1. 의존성 배열 X
: 의존성 배열을 가지지 않으므로 컴포넌트가 렌더링될 때마다 실행됨
: 값이 변하면 실행되는 함수로 불필요한 사용이 너무 많아 거의 사용되지 않음
    useEffect(() => {side effect})

2. 의존성 배열 O
: 처음 마운트 될 때만 실행되므로 컴포넌트의 상태나 프롭스가 변경되는 경우에는 useEffect 함수가 다시 실행되지 않음
: Ajax 요청과 같은 초기화 작업에 사용할 수 있음, 

① useEffect( () => {side effect}, [ dependency arrangement ]);
빈 배열: 컴포넌트가 나타날 때 딱 1번만 함수가 호출
② useEffect( () => {}, [ props ]);
props: 특정 props(외부 변수)가 바뀔 때마다 함수가 호출

useEffect 라는 Hook(use-로 시작하는 함수)을 사용하여 할 수 있는 3가지 동작
- 컴포넌트가 마운트 됐을 때 (처음 나타났을 때)
- 언 마운트 됐을 때 (사라질 때)
- 업데이트될 때 (특정 props가 바뀔 때)

useEffect.cleanup(): useEffect [ ] 안에 내용이 비어 있는 경우에 컴포넌트가 사라질 때 cleanup 함수가 호출
 = 메모리 해제, 이벤트 해제(Scanner close의 의미)
*/