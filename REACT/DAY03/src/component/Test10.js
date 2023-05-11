import React, { useRef, useState } from 'react';

const Test10 = () => {
    const seq = useRef(1);

    const [data, setData] = useState([]); // 빈 배열로 선언


    const [formData, setFormData] = useState({
        name: '',
        age: ''
    }) // 객체 초기화
    const {name, age} = formData;


    const nameRef = useRef();
    
    const onInput = (e) => {
        const {name, value} = e.target; // 이벤트가 발생한 태그

        setFormData({ // 객체 - 복사
            ...formData, 
            [name]: value
        })
    }

    const onAdd = (e) => {
        e.preventDefault();
        // WebBrowser 고유의 동작을 중단시켜주는 역할(submit에 따른 페이지 이동 방지)
        if(!name || !age) {return;}

        setData([ // 배열 - 추가
            ...data,
            {
                seq: seq.current++,
                name, // name: name, key-Value 이름이 동일할 경우 1번만 작성 가능
                age // age: age
            }

        ])

        // 초기화
        setFormData({
            name: '',
            age: ''
        })

        // 포커스 조정
        nameRef.current.focus();
    }

    return (
        <div>
            <form onSubmit={onAdd}>
                이름: <input type='text' name='name' value={name} onChange={onInput} ref={nameRef} />
                {/* 상태변수를 선언하여 변화되는 값을 즉각적으로 반영하고자 함*/}
                <br/>
                나이: <input type='text' name='age' value={age} onChange={onInput} />
                <br/>
                <button type='submit'>추가</button>
            </form>

            <hr />

            <ul>
               {
                    data.map(item => <li>
                                        {item.seq} : {item.name} : {item.age}
                                     </li>)
               } 
            </ul>

        </div>
    );
};

export default Test10;