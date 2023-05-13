import React, { useEffect, useRef, useState } from 'react';
import style1 from '../css/Todos.module.css';
import TodoForm from './TodoForm';
import TodoList from './TodoList';

const Todos = () => {
    // const [data, setData] = useState([]);
    // 데이터 배열이 아닌 Local Storage에 Data 저장
    
    // Local Storage 읽어오기
    const [data, setData] = useState(JSON.parse(localStorage.getItem('data'))||[]);
    // localStorage.getItem('data'): 브라우저의 로컬 스토리지에서 data라는 키로 저장된 값을 가져옴
    // JSON.parse(): JSON 형태의 문자열을 자바스크립트 객체로 변환하는 함수
    // cf. JSON.stringify(): 자바스크립트 객체를 JSON 형태의 문자열로 변환하는 함수
    // || []: 로컬 스토리지에 data라는 키로 저장된 값이 없으면 null을 반환하고, 이 경우 []를 기본값으로 사용

    // cf. JS 객체: 다른 객체나 배열, 함수 등 다양한 데이터 타입이 포함될 수 있으며, 객체의 속성 이름은 따옴표("")를 사용하지 않아도 됨
    //     JSON: 객체의 속성 이름을 반드시 따옴표("")로 감싸야 하며, 객체 안에 함수를 포함시킬 수 없음

    // Local Storage 저장하기: F12 Application Storage 참고
    useEffect (() => {
        localStorage.setItem('data', JSON.stringify(data));
        // 변수명, JSON 데이터
        // data: {seq:2, text: '할 일 1'}
    }, [data]) // data에 변화가 생길 때마다 useEffect 실행


    const seq = useRef(data.length + 1); // 데이터 크기에 따른 시퀀스 초기값 설정

    const onAdd = (text)=>{
        // <TodoForm />로부터 입력데이터가 넘어옴
        setData([
            ...data, // 배열: 복사 후 새로 추가
            {
                seq: seq.current++,
                text // key-value가 같을 경우 1번만 작성 가능
            }
        ])
    };

    const onDelete = (seq) => {
    // 유일한 key 값을 이용하여, 데이터가 선언된 구간을 삭제
        setData(data.filter(item => item.seq !== seq));
        // 넘겨받은 seq값이 아닌 것만 filter를 통과해서 반환
    }
    // 데이터를 제거(데이터의 출력을 제한)하는 함수이므로, 데이터가 선언된 Todos에서 함수를 생성해야 함

return (
        <div className={style1.Todos}>
            <h1>일정 관리</h1>
            <TodoForm onAdd={onAdd}/>
            <TodoList data={data} onDelete={onDelete} />
        </div>
    );
};

export default Todos;


/*
데이터 읽기, 쓰기

Web Storage: Local Storage, Session Storage

특징
- 형식(JSON 형식): (key, value)
- 클라이언트에 대한 정보 저장
- 로컬에만 정보를 저장, 쿠키는 서버와 로컬에 정보를 저장

종류
- LocalStorage: 클라이언트에 대한 정보를 영구적으로 저장
                브라우저가 종료되도 정보는 영구적으로 저장되나, 브라우저상에서 데이터 조회가 가능하여 보안에 취약
- SessionStorage: 세션 종료 시(브라우저 닫을 경우) 클라이언트에 대한 정보 삭제

장점
- 서버에 불필요하게 데이터를 저장하지 않는다. (백엔드에 절대로 전송되지 않는다.)
- 저장 가능한 데이터의 용량이 크다. (약 5Mb, 브라우저마다 차이 존재)

단점
- HTML5를 지원하지 않는 브라우저의 경우 사용 불가.
*/