import React, { useEffect, useMemo, useState } from 'react';

const user = [
    {id: 1, name: '홍길동'},
    {id: 2, name: '김수혁'},
    {id: 3, name: 'cat'},
    {id: 4, name: 'DAUM'},
    {id: 5, name: '김수혁'},
    {id: 6, name: 'daum'},
    {id: 7, name: '마동석'},
    {id: 8, name: 'naver'},
    {id: 9, name: '김희선'},
    {id: 10, name: 'NAVER'}
]

const Test05 = () => {

    let [data, setData] = useState(user);
    const [text, setText] = useState('');
    const [search, setSearch] = useState('');



/*
    useEffect(() => {
        // data로 설정할 경우, filter결과가 data가 되므로 user 사용
        // 필터링 된 데이터에서 데이터를 재 필터링하는 문제 발생
        // setData(user.filter(item => item.name.toLowerCase().indexOf(text.toLowerCase())!== -1))
        setData(user.filter(item => item.name.toLowerCase().includes(text.toLowerCase())))
    })
*/



    /*
        검색어가 입력될 떄마다, 값을 업데이트
        // onSearch 함수가 호출되는 시점은 실제로는 검색 버튼이 클릭되었을 때이지만,
        // onSearch 함수에서는 text 상태값이 입력될 때마다 search 상태값(setData)이 변경
        // onSearch 함수에서 setSearch(text)를 호출 ▶ 입력된 text 값이 search 상태값으로 업데이트

        // click 이벤트 없이도 data가 변경되어 출력???????????????????

        const onSearch = () => {
            // setData(user.filter(item => item.name.toLowerCase().indexOf(text.toLowerCase())!== -1))
            setData(user.filter(item => item.name.toLowerCase().includes(text.toLowerCase())))
        }
    */


    // searchBtn
    data = useMemo(() => {
        return user.filter(item => item.name.toLowerCase().includes(text.toLowerCase()));
    }, [search])
    // data 재할당을 위해서 useState 선언을 const -> let으로 변경


    const onSearch = () => {
        setSearch(text);
    }

    


    return (
        <div>
            <input type='text' value={text} onChange={e => setText(e.target.value)} />
            {/* 상태변수 선언 후, value를 지정할 경우 useState의 setter를 통해서만 값을 변화시킬 수 있음 */}
            <button onClick={onSearch}>검색</button>

            <ul>
            {// 코드 블록 내부에 JSX 표현식(map)을 작성하기 위해 중괄호 {}를 사용
                data.map(item => <li key={item.id}>
                    {item.id} : {item.name}
                </li> )
            }
            </ul>
        </div>
    );
};

export default Test05;