import React from 'react';
import { useParams } from 'react-router-dom';

const Front = ({ data }) => {
    const { name } = useParams(); // 비구조 할당
    // path='/front/:name(html, css, javascript, react, vue)'를 받기위해 사용

    return (
        <div>
            <h1>FRONT PAGE</h1>
            <h1>{name}</h1>
            <hr />
            {
                data.filter(item => item.title === name)
                    .map((item, index) => <div key={index}>
                        <h2>{item.title} / {item.info}</h2>
                    </div>)
            // filter 메서드는 여러 항목을 반환할 수 있으므로, map 함수를 적용하여 각 항목에 대해 동일한 작업을 수행할 수 있음
            }
        </div>
    );
};

export default Front;