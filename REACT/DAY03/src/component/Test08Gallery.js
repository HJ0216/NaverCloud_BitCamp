import React, { useState } from 'react';

import Test08View from './Test08View';
import dataList from './Test08Data';
// import 참조변수 from 경로;

import '../css/Test08.css'

const Test08Gallery = () => {

    const [data, setData] = useState(dataList);
    const [one, setOne] = useState(data[0]);

    const onView = (id) => {
        setOne(data[id-1]); // id와 배열번호 차이 조정
    }

    return (
        <div className='wrap2'>
            <Test08View one={one} data={data} onView={onView} />
        </div>
    );
};

export default Test08Gallery;

/*
- src 안에 있는 이미지 파일 처리는 참조변수를 사용한다.
  import 참조변수 from '이미지경로';
*/