import React, { useReducer, useState } from 'react';

// 초기값
const initialState = 0;

// useReducer(): reducer: state(변수 상태), action을 전달받아 새로운 state(변수 상태)를 반환하는 함수
const reducer = (state, action) => {
    switch(action.type) {
        case 'INCREMENT'
         : return state + 1
        case 'DECREMENT'
         : return state - 1
        case 'RESET'
         : return 0
        default // switch-default: 반드시 작성해야 함
         : return false
         // state값이 false가 됨
         // JS에서는 false와 0과 다르게 취급됨
    }
}


/*
useState(): 변수 선언과 함수 정의를 함께해야 함
const [count, setCount] = useState(0);

const onIncresement = () => {
    setCount(count + 1);
}
const onDecresement = () => {
    setCount(count - 1);
}
const onReset = () => {
    setCount(0);
}

 * useReducer(): 변수 선언과 함수 정의를 분리할 수 있음
*/


const Test01 = () => {
    // const [count, setCount] = useState();
    const [count, dispatch] = useReducer(reducer, initialState);
    // count → state로 전달, dispatch → action으로 전달(reducer가 건네 받음)

    return (
        <div>
            <h1>카운트: {count}</h1>
            <p>
                <button onClick={() => dispatch({type: 'INCREMENT'})}>증가</button>
                {/* 
                    dispatch 함수를 호출하여 액션 객체를 전달하는 코드, {type: 'INCREMENT'} 객체를 전달
                    ▶ 이를 처리하기 위한 리듀서 함수가 실행
                */}
                <button onClick={() => dispatch({type: 'DECREMENT'})}>감소</button>
                <button onClick={() => dispatch({type: 'RESET'})}>초기화</button>
            </p>
        </div>
    );
};

export default Test01;

/*
useReducer()
: 현재 컴포넌트가 아닌 다른 곳에 state를 저장

[사용법]
const [state, dispatch] = useReducer(reducer, initialState);
state : 현재 상태
dispatch : action을 발생시키는 함수
reducer : state(state)와 action(dispatch)를 받아 새로운 state를 반환하는 함수
initialState : 초기값

const reducer = (state, action) => {}
useReducer로부터 state(state)와 action(dispatch)를 건내받아 새로운 state를 반환
*/