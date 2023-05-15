import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Member from './Member';


const Main = () => {
    const [data, setData] = useState([]);
    // TypeError: Cannot read property 'map' of undefined
    // 첫 렌더시 state 값을 받아오지 못해 undefined 로 들어가 생기는 이슈이기 때문에 초기값을 설정
    // 초기값 {} 빈 배열 선언

    useEffect(() => {
        axios.get('https://jsonplaceholder.typicode.com/users')
            .then(res => setData(res.data))
            // res.data: 응답객체의 prop: data, url에서 가져온 데이터 배열
    }, [])
    // 첫 렌더 시, 한 번만 data를 읽으므로 [] 기재 필수

    return (
        <div>
            <h1>MAIN PAGE / TOTAL MEMEBER: {data.length}</h1>
            {/* Using Map

            <ul>
                {
                    data.map((item, index) => 
                        <li key={index}>{item.id} / {item.name} / {item.email}</li>
                    )
                }
            </ul>
            */}

            {/* Using Component */}
            {
                data.map(item => <Member key={item.id} item={item} />)
                // item: 1인분 data, <Member /> = DTO
            }
        </div>
    );
};

export default Main;