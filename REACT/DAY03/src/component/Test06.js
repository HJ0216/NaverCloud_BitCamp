import React, { useRef, useState } from 'react';

const Test06 = () => {
    const nameRef = useRef(); // ref: 값이 변화가 없는 상수의 개념

    // const[name, setName] = useState('');
    // const[id, setId] = useState('');
    // const[pwd, setPwd] = useState('');

    // onChange 함수 내용이 동일할 경우, 같은 내용의 메서드를 중복해서 작성해야하는 문제 발생
    // 동일한 역할을 하는 메서드의 이름을 통일시켜 코드를 재사용성을 높임

    const [data, setData] = useState({
    // data: 상태변수 1개가 아닌 3개의 상태변수의 집합    
        name: '',
        id: '',
        pwd: ''
    });

    const {name, id, pwd} = data; // 비구조 할당

    const onInput = (e) => {
        const {name, value} = e.target;
        // 이벤트가 발생한 input 태그가 여러개이므로 호출한 input tag를 찾기 위해 name도 추가로 받아옴
        setData({
            ...data, // 1. 새롭게 주입하고자하는 data 값을 복사
            // [...] 배열 복사, {...} 객체 복사)(객체 내부의 속성을 그대로 복사하면서도 원하는 속성만 업데이트할 수 있음)
            // 복사하지 않을 경우, 상태값의 일부가 유지되어야 하는 경우에도 이전 값을 복사하지 않고 바로 덮어쓰기 때문에 이전에 입력된 값이 유지되지 않음
            // 예: 이름 입력 후, id를 입력하면 이름 입력값({name})이 사라짐

            [name]: value // 2. []에 해당하는 입력된 변수, name 값(name or id or pwd)에 해당하는 속성이 동적으로 업데이트됨
            // 3. 수정한 상태값을 setData 함수를 사용하여 업데이트
        });
    }

    const onReset = () => {
        setData({
            name: '',
            id: '',
            pwd: ''
        });

        nameRef.current.focus();
        // nameRef 선택 후, focus()
    }

    return (
        <div>
            <table border='1' cellPadding='5' cellSpacing='0'>
                <tr>
                    <th width='100'>NAME</th>
                    <td>
                        <input type='text' name='name' value={name} onChange={onInput} ref={nameRef} />
                    </td>
                </tr>

                <tr>
                    <th width='100'>ID</th>
                    <td>
                        <input type='text' name='id' value={id} onChange={onInput} />
                    </td>
                </tr>

                <tr>
                    <th width='100'>PWD</th>
                    <td>
                        <input type='password' name='pwd' value={pwd} onChange={onInput} />
                    </td>
                </tr>

                <tr>
                    <td colSpan='2' align='center'>
                        <button onClick={onReset}>초기화</button>
                    </td>
                </tr>
            </table>

            <h3>Name: {name}</h3>
            <h3>ID: {id}</h3>
            <h3>PWD: {pwd}</h3>
        </div>
    );
};

export default Test06;