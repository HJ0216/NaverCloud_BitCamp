import React, { useState } from 'react';
import dataList from './CatData.js';
import CatList from './CatList.js';
import '../css/reset.css'
import '../css/style.css'

const Cat = () => {

    const[data, setData] = useState(dataList)
    // dataList를 data 상태 변수에 저장

    return (
        <>
            <section className='business'>
                <div className='inner'>
                    <h2>고양이</h2>
                    <p>고양이들</p>
                    <CatList data={data}/> {/* Cat.js ▶ CatList.js 데이터 전달 */}
                </div>    
            </section>
        </>
    );
};

export default Cat;