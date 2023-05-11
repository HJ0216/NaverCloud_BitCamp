import React from 'react';
import Test08Item from './Test08Item';


const Test08List = ({data, onView}) => {
    return (
        <ul className='list'>
            {
                data.map(item => <Test08Item key={item.id} item={item} onView={onView} />)
                // 중괄호 생략 시, return 생략 가능
                // 1. data 배열에 있는 각각의 요소에 대해 item이라는 내장 객체 생성
                // 2. data에서의 각 요소를 가리키는 변수인 item에 저장된 정보를 Test08Item 컴포넌트에게 props로 전달
                // 3. Test08Item 컴포넌트가 그 정보를 화면에 렌더링 
                //    * key 속성은 React에서 배열 내 요소를 구분하기 위해 사용, 중복되지 않은 값을 지정해야 함
            }
        </ul>
    );
};

export default Test08List;