import React, {useRef, useState} from 'react';


const Test01 = () => {
    const [id, setId] = useState(''); // 빈값 또는 '' 사용 가능
    const [pwd, setPwd] = useState('');
    // 입력을 안할 경우, null로 인식 > null.length 사용 X

    const idRef = useRef();

    const onChangeId = (event) => {
        const {value} = event.target; // 이벤트가 발생한 대상(태그)
        // 비구조할당: input tag 안에 있는 모든 속성들(type, value, onChange) 중에서 value 값만 가져옴
        setId(value);
    };
    const onChangePwd = (e) => {
        const{value} = e.target;
        setPwd(value);
    };
    const onReset = () => {
        setId('');
        setPwd('');

        idRef.current.focus(); // idRef가 호출된 태그에 포커스
    }

    return (
        <div>
            아이디: <input type='text' value={id} onChange={onChangeId} ref={idRef} />
            <br/>
            비밀번호: <input type='password' value={pwd} onChange={onChangePwd} />
            <br/>
            {<button disabled={pwd.length< 6}>로그인</button>}&emsp;
            {/* <button disabled={pwd.length >= 6 ? false : true}>로그인</button> &emsp; */}
            <button onClick={onReset}>초기화</button>
        </div>
    );
};

export default Test01;


/*

* 특정 DOM 을 선택해야 하는 상황
- JavaScript: getElementById, querySelector 같은 DOM Selector 함수 사용
- React: Hooks - useRef 사용 ▶ useRef 훅이 반환하는 ref 객체를 이용해서 자식 요소에 접근 가능


리액트 컴포넌트: State가 변할 때마다 다시 렌더링이 되면서 컴포넌트 내부 변수들이 초기화 됨
단, Ref(Static)안에 있는 값은 아무리 변경해도 컴포넌트는 다시 렌더링 되지 않음
즉, 컴포넌트가 아무리 렌더링이 되어도 Ref안에 저장되어 있는 값은 변화되지 않고 그대로 유지 됨

사용 순서
1. Ref 객체 선언 
const nameRef = useRef();

2. 선택하고 싶은 DOM에 속성으로 ref 값 설정
<input ref = { nameRef } />

3. Ref 객체의 current: 선택하고자 하는 DOM
   이후 포커싱을 해주는 DOM API focus()를 호출

*/
