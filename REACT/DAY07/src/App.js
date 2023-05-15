import React from 'react';
import App01 from './App01';
// import App02 from './App02';
import App03 from './App03';
import App04 from './App04';
import App05 from './App05';

// style이 겹치므로 import된 컴포넌트를 주석처리

const App = () => {
  return (
    <div>
      {/* <App01 /> */}
      {/* <App02 /> */}
      {/* <App03 /> */}
      {/* <App04 /> */}
      <App05 />
    </div>
  );
};

export default App;


/*
React-Router

1. 리액트: SPA (Single Page Application) 방식
클라이언트 요청 ▶ 서버에서 요청한 페이지를 반환

cf. MPA 방식: 여러 개의 페이지를 사용하며 새로운 페이지를 로드(이전)
* React: 새로운 페이지를 로드하지 않고 하나의 페이지 안에서 필요한 데이터만 가져오는 형태

2. 사용자가 입력한 주소 감지
 * 환경에 따라 다양한 라우터 사용 가능
   가장 많이 사용하는 라우터 컴포넌트: BrowserRouter, HashRouter


[설치]
npm install react-router-dom
yarn add react-router-dom


# react-router-dom 변경사항 (2021. 11. 25 기준) 
 1. Route 컴포넌트: Routes 컴포넌트로 묶어야 함
 2. Route 컴포넌트의 매개변수:  compenent → element
 3. useHistory → useNavigate
 4. history.push('/') -> navigate('/')


Route : 경로에 따른 컴포넌트 반환
Link : Router 주소 변경, a 태그이지만 새로 고침 동작X
→ 새로고침 동작이 없으므로 페이지 내 정보를 안정적으로 들고 이동 할 수 있음


useParams() 
:name 는 name이 key가 되어 parameter로 불려옴

1. Link
- 클릭 시 바로 이동
  ex) 상품 리스트에서 상세 페이지 이동 시
- react-router-dom: Link component =  DOM: a tag
* a 태그와 Link 차이
  a : 외부 프로젝트로 이동하는 경우
  Link : 프로젝트 내에서 페이지 전환하는 경우

  
2. useNavigate
- 페이지 이동 함수 반환
  반환하는 함수를 navigate라는 변수에 저장 후 navigate의 인자로 설정한 path 값을 넘겨주어 경로 이동
- 페이지 전환 시 추가로 처리해야 하는 로직이 있으면 useNavigate 사용
  ex) 로그인 버튼 클릭 시
      회원가입 되어 있는 사용자 -> Main 페이지로 이동
      회원가입이 되어 있지 않은 사용자 -> SignUp 페이지로 이동

Index Routes
- index: default routes
*/