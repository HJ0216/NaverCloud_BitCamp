import React from 'react';
import '../css/Test03.css'
import myStlye from '../css/Test03.module.css'
// 참조변수(myStlye) 선언 필요

const Test03 = () => {
    return (
        <div>
            <div className='box'>Test(Test03.css)</div>
            <div className={myStlye.box}>Test(Test03.module.css)</div>
            {/* 개발자 도구: <div class=Test03_box__yo5YW>Test(Test03.module.css)</div> */}
        </div>
    );
};

export default Test03;


/*
컴포넌트별 CSS 작성
- 파일명.module.css
- import 참조변수 form './파일명.module.css';
- 개발자 도구 className: 파일명_클래스명_랜덤값
*/