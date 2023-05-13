import React, { useEffect, useReducer } from 'react';
import axios from 'axios';

// axios install: [terminal] npm install axios

const initialState = {
    data: {},
    error: null,
    loading: true
    // 비동기작업에서 요청에 대한 응답을 받기 전까지 대기함을 의미
};

const reducer = (state, action) => {
    switch(action.type){
        case 'SUCCESS':
            return {
                data: action.payload, // payload: 전송되는 데이터
                error: null,
                loading: false
            } // 반환되는 state
        case 'ERROR':
            return {
                data: {},
                error: 'ERROR',
                loading: true
            }
        default:
            return state;
    }
}

const Test04 = () => {
    const [state, dispatch] = useReducer(reducer, initialState);

    useEffect(() => {
        const url = 'https://jsonplaceholder.typicode.com/posts/3';
        axios.get(url)
             .then(res => { // res: axios.get() - HTTP GET 요청 ▶ 서버에서 반환한 응답 객체
                dispatch({type: 'SUCCESS', payload: res.data})
                // res.data: url에 대한 GET 요청으로 받아 온 서버의 응답에서 반환되는 JSON 데이터
             }) // 성공
             .catch(error => {
                dispatch({type: 'ERROR'})
             }) // 실패
    }, []);

    return (
        <div>
            <h2>
                {
                    state.data && state.loading ? '로딩중' : state.data.title
                    // ERROR일 경우에도 데이터는 {}, 빈 객체를 반환하므로 true가 되어 state.loading으로 넘어갈 수 있음
                    // data가 success일 경우, loading: false
                    // data가 error일 경우, loading: true
                }
            </h2>
            <p>
                {
                    state.error ? state.error : null
                }                
            </p>
        </div>
    );
};

export default Test04;