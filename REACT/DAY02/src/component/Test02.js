import React from 'react';

const Test02 = () => {

    const title = '신상명세서';
    const arr = ['홍길동', '코난', '둘리', '라이언', '네오'];
    const data = [
        {id: 1, name: '홍길동'},
        {id: 2, name: '코난'},
        {id: 3, name: '둘리'},
        {id: 4, name: '라이언'},
        {id: 5, name: '네오'}
    ]

    return (
        <div>
            <h2>{title}</h2>
            <ul style={{border: '2px solid #666666'}}>
            {/* style 작성 시, 반드시 중괄호 2번 기재 */}
                { // {} 작성을 통한 script 구역 선언
                    arr.map((item, index) => {
                        return(<li key={index}>{index} : {item}</li>)
                        /* 
                        li에 key값을 부여
                        고유한 key 값을 사용하여 React가 각 항목을 정확하게 식별하고 업데이트하는 데 도움을 줌 
                        */
                    }) /* 중괄호 기재 시, return 기재 필수 */
                } {/* JSX 문법에서 JavaScript 코드를 삽입하는 역할 */}
            </ul>
            <hr/>
            <ul style={{border: '2px solid dodgerblue'}}>
                {/*
                    data.map((item, index)=>{
                        return(<li key={index}>{item.id} : {item.name}</li>)
                    })
                */
                    data.map( item =>{
                        return(<li >{item.id} : {item.name}</li>)
                    })
                }
            </ul>
        </div>
    );
};

export default Test02;