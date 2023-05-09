import React, { useState } from 'react';

const Test04 = () => {
    const [visible, setVisible] = useState(true)

    const onShow = () => {
        setVisible(true);
    }

    const onHide = () => {
        setVisible(false);
    }

    const onToggle = () => {
        // visible ? setVisible(false) : setVisible(true);
        setVisible(!visible); // 현재값과 반대값으로 설정
    }

    return (
        <div>
            <button onClick={onShow}>보이기</button>            
            <button onClick={onHide}>숨기기</button>
            <button onClick={onToggle}>{visible ? '숨기기' : '보이기'}</button>
            {/* 글자 작성 시, ''로 묶어주기 */}

            <hr/>

            {
                // visible ? <div style={{width: 300, height: 300, margin: 20, background: 'tomato'}}></div> : null
                visible && <div style={{width: 300, height: 300, margin: 20, background: 'tomato'}}></div>
            }
        </div>
    );
};

export default Test04;