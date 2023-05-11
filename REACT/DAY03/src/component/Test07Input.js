import React from 'react';

const Test07Input = ({name, age, addr, phone, onInput, onNext}) => {
// <Test07Input data={data} onInput={onInput} />로 컴포넌트 선언한 경우,
// const Test07Input = ({data, onInput}) => {
// const {name, age, addr, phone} = data;

    return (
        <div>
            <p>
                <label>이름</label>
                <input type='text' name='name' value={name} onChange={onInput} />
                <br/><br/>
                <label>나이</label>
                <input type='text' name='age' value={age} onChange={onInput} />
                <br/><br/>
                <label>주소</label>
                <input type='text' name='addr' value={addr} onChange={onInput} />
                <br/><br/>
                <label>핸드폰</label>
                <input type='text' name='phone' value={phone} onChange={onInput} />
                <br/><br/>
                <button onClick={onNext}>다음</button>
            </p>
        </div>
        /* 
            <table>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type='text' name='name' value={name} onChange={onInput} />
                    </td>
                </tr>
                <tr>
                    <th>나이</th>
                    <td>
                        <input type='text' name='age' value={age} onChange={onInput} />
                    </td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>
                        <input type='text' name='addr' value={addr} onChange={onInput} />
                    </td>
                </tr>
                <tr>
                    <th>핸드폰</th>
                    <td>
                        <input type='text' name='phone' value={phone} onChange={onInput} />
                    </td>
                </tr>
            </table>
        */
    );
};

export default Test07Input;