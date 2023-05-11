import React, { useState } from 'react';
import Test07Input from './Test07Input';
import Test07Print from './Test07Print';
import Test07Output from './Test07Output';
import '../css/Test07.css'

const Test07Main = () => {
    const [data, setData] = useState({
        name: '',
        age: '',
        addr: '',
        phone: ''
    });

    const {name, age, addr, phone} = data

    const onInput = (e) => {
        const {name, value} = e.target;
        setData({
            ...data, // 이전 상태 값을 복사(값을 업데이트할 때, 기존 입력된 값이 유지되도록 함)
            [name]: value // name 속성(name or age or addr or phone)에 해당하는 값을 새로운 value로 대체
        });
    }


    const[count, setCount] = useState(1);
    // count=1: input, count=2: print, count=3: output

    const onPrev = () => {
        setCount(count-1);
    }
    const onNext = () => {
        setCount(count+1);
    }


    return (
        <div className='wrap'>

            {
                count === 1 && <Test07Input name={name} age={age} addr={addr} phone={phone} onInput={onInput} onNext={onNext} />
                // <Test07Input data={data} onInput={onInput} />
                // data={data}: data 또는 props로 받을 수 있음
                // {...data}: {name, age, addr, phone} 또는 props로 받을 수 있음
            }

            {
                count === 2 && <Test07Print name={name} age={age} addr={addr} phone={phone} onInput={onInput} onNext={onNext} onPrev={onPrev} />
                //count === 2 && <Test07Print {...data} onInput={onInput} onNext={onNext} onPrev={onPrev} />
            }

            {
                count === 3 && <Test07Output name={name} age={age} addr={addr} phone={phone} onInput={onInput} />
                /* <Test07Output name={name}> 필요한 대상만 전달 */
            }
        </div>
    );
};

export default Test07Main;