import React, {useState} from 'react';

const Test02 = () => {
    const [check, setCheck] = useState(true);

    const onCheck = (e) => {
        const {checked} = e.target;
        //  e.target.checked 값에서 checked라는 변수를 선언하고, 그 값을 가져옴
        //  나중에 checked 변수를 사용할 때 e.target.checked 대신에 checked 변수를 사용할 수 있음
        // 비구조화 할당을 사용하여 코드를 간결하게 작성할 수 있음
        setCheck(checked);
    }
    // 이벤트가 발생한 input 태그의 checked 속성 값을 받아와서 setCheck 함수에 전달하여 check 상태를 변경

    return (
        <div style={{color: check ? 'dodgerblue' : 'tomato', fontSize: 25, margin: 30}}>
            <label>
                <input type='checkbox' checked={check} onChange={onCheck} />Have a nice day
                {/* useState를 통해서 checked의 속성값인 check 상태변화 설정 */}
            </label>
        </div>
    );
};

export default Test02;