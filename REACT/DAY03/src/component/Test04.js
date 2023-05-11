import React, {useState} from 'react';
import Animal from './Animal';
import Display from './Display';

const Test04 = () => {
    const[name, setName] = useState('호랑이');
    // Animal 입력 변수를 Display 출력 변수로 전달
    // Animal과 Display를 형제관계로 선언하여 사용하기 위함
    // Animal과 Display를 상하관계로 선언 시, Animal 데이터를 Display로 전달 가능

    const onInputName = (e) => {
        const {value} = e.target;
        // 비구조할당: e.target.value ▶ value
        setName(value);
    }

    return (
        <div>
            <Animal name={name} onInputName={onInputName} />
            {/* 변수를 주고받을 때, 주는 순서와 받는 순서를 맞춰야 함 */}
            <Display name={name}/>
        </div>
    );
};

export default Test04;