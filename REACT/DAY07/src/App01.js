import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import Home from './page01/Home';
import About from './page01/About';
import Ceo from './page01/Ceo';
import Sub01 from './page01/Sub01';
import NotFiles from './page01/NotFiles';

const App01 = () => {
    return (
        <BrowserRouter>
            <>
                <nav>
                    <ul>
                        <li><Link to=''>Home</Link></li>
                        {/*
                            '/' 생략 시, index로 이동
                            <a href="/">Home</a> 대신하여 <Link> 사용
                        */}
                        <li><Link to='/about'>About</Link></li>
                        <li><Link to='/ceo'>Ceo</Link></li>
                        <li><Link to='/sub01'>Sub01</Link></li>
                    </ul>
                </nav>

                {/* 화면에 반환되는 영역 */}
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/about' element={<About />} />
                    <Route path='/ceo' element={<Ceo />} />
                    <Route path='/sub01' element={<Sub01 />} />
                    <Route path='*' element={<NotFiles />} />
                </Routes>
            </>
        </BrowserRouter>
    );
};

export default App01;