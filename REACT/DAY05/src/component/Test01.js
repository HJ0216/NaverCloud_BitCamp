import React, { useEffect, useState } from 'react';
import axios from 'axios'; // axios 식별자 부여

const Test01 = () => {
    const[data, setData] = useState([]);

    // 1번
/*
    useEffect(() => {
        fetch('https://jsonplaceholder.typicode.com/posts')
        .then(res => res.json()) // JSON으로 변환(fetch에서는 필수적으로 사용)
        .then(res => setData(res)) // Data에 저장
    }, [])
    // useEffect가 마운트 될 때 한 번 호출됨
    // fetch: $.ajax-url
    // then: $.ajax-success
    // res: response(응답 객체)
*/



    // 2번
/*
    useEffect(() => {
        axios.get('https://jsonplaceholder.typicode.com/posts')
        .then(res => setData(res.data)) // 응답 객체(res)의 data를 주입
    }, [])
*/



    // 3번
/*
    useEffect(() => {
        // 비동기 통신: 요청 후 응답을 기다리지 않음
        
        // 동기와 비동기 차이
        // 동기: 요청 후 응답을 받을 때까지 다른 작업을 수행하지 않음
        // 비동기: 요청 후 응답을 받기 전에 다른 작업을 수행함
        // 단, 비동기의 경우 응답 받기 전에도 작업을 수행하므로 응답을 받아서 할 수 있는 작업은 할 수 없음

        const getData = async() => {
            const res = await fetch('https://jsonplaceholder.typicode.com/posts');
            const data = await res.json();
            setData(data);
        }

        getData();

    }, [])     
*/



    // 4번
    useEffect(() => {
        // 비동기 통신: 요청 후 응답을 기다리지 않음
        const getData = async() => {
            const res = await axios.get('https://jsonplaceholder.typicode.com/posts');
            setData(res.data);
        }

        getData();

    }, [])     

    return (
        <div>
            <ul>
                {
                    data.map(item => <li key={item.id}>
                        {item.id} / {item.title}
                    </li>)
                }
            </ul>
        </div>
    );
};

export default Test01;



/*
비동기 통신 - ajax
서버에 새로고침 없이 요청할 수 있게 도와준다.
서버로 네트워크 요청을 보내고 응답을 받을 수 있도록 도와준다.

$.ajax({
    type: '',
    url: '',
    success: '',
    error: ''
});

1. jQuery - $.ajax()
2. js - fetch() -> json 형식으로 가져온다.
      -  axios.get() -> object 형식으로 가져온다. 

fetch( url, [options] )

fetch(url)
.then(콜백) - 응답 성공, success
.catch(콜백) - 응답 실패, error

axios.get(url)
     .then(콜백) - 응답 성공
     .catch(콜백) - 응답 실패

npm install axios / yarn add axios
*/