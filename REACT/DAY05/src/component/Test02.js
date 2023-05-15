import axios from 'axios';
import React, { useEffect, useState } from 'react';

const Test02 = () => {
    const [id, setId] = useState('1');
    // 상태변수 사용
    // 1. 변화된 값이 필요한 구간 선언
    // 2. useState 선언
    const [dto, setDto] = useState({});
    // JSON record 1개를 받는 객체가 필요
    const [search, setSearch] = useState(1);
    // 클릭 시마다 useEffect 작동을 시키기 위해 search 변수 생성
    // set을 통해서 클릭 후, 값이 변화하므로 useEffect 변수로 쓰이기 적당
    // id의 경우, onChange에 걸려있으므로 사용할 수 X

    const onSearch = (id) => {
        setSearch(id);
    }
    // <button onClick={() => onSearch(id)}>검색</button> 값이 들어옴
    // 변수를 다른 이름으로 선언해도 되지만, 통일성을 위해 호출한 곳에서 전달한 변수명과 통일시킴

    useEffect(() => {
        axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`)
        // 전달받은 id 값에 맞춰서 데이터를 필터링 후 호출
        .then(res => setDto(res.data))
        // 조회된 1개의 데이터를 저장시킬 dto 선언 필요
    // }, [id]) // id value 값이 바뀔 때마다 reLoad
    }, [search])
    // 버튼을 눌렀을 때마다 reLoad, 버튼 클릭 ▶ search 값 변화
    // [onSearch] or [() => onSearch] 가능
    // }, [search, id])

    return (
        <div>
            <input type='text' value={id} onChange={e => setId(e.target.value)}/>
            {/*
                변경 시, 이벤트가 발생한 태그의 value를 id 변수에 주입
                value 값은 {id}로 입력받으므로 onChange를 통해서만 값이 변경됨
            */}
            <button onClick={() => onSearch(id)}>검색</button>
            {/* <button onClick={onSearch(id)}>검색</button> onload 함수 */}

            <hr/>

            <h3>{dto.id} / {dto.title}</h3>
        </div>
    );
};

export default Test02;