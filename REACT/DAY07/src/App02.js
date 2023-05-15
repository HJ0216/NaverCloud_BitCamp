import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import NavBar from './page02/NavBar'
import Home from './page02/Home';
import About from './page02/About';
import Ceo from './page02/Ceo';
import Sub01 from './page02/NavBar';
import NotFiles from './page02/NotFiles';

import './css/style02.css'
// style이 모든 파일에 적용되어야하므로 전체 Component에 적용

const App02 = () => {
    return (
        <BrowserRouter>
            <>

                <NavBar />

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

export default App02;