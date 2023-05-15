import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import Home from './page03/Home';
import About from './page03/About';
import Profile from './page03/Profile';
import Front from './page03/Front';

const data = [
    { title: 'html', info: 'HTML INFO' },
    { title: 'css', info: 'CSS INFO' },
    { title: 'javascript', info: 'JAVASCRIPT INFO' },
    { title: 'react', info: 'REACT INFO' },
    { title: 'vue', info: 'VUE INFO' },
]

const App03 = () => {
    return (
        <div>
            <BrowserRouter>
                <>
                    <ul>
                        <li><Link to='/'>HOME</Link></li>
                        <li><Link to='/about'>ABOUT</Link></li>
                        <li><Link to='/profile'>PROFILE</Link></li>

                        <li><Link to='/front/html'>html</Link></li>
                        <li><Link to='/front/css'>css</Link></li>
                        <li><Link to='/front/javascript'>javascript</Link></li>
                        <li><Link to='/front/react'>react</Link></li>
                        <li><Link to='/front/vue'>vue</Link></li>
                    </ul>
                </>


                {/* 화면에 반환되는 영역 */}
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/about' element={<About />} />
                    <Route path='/profile' element={<Profile />} />
                    <Route path='/front/:name' element={<Front data={data} />} />
                </Routes>
            </BrowserRouter>

        </div>
    );
};

export default App03;


/*
 :style 를 route path에 사용 → useParams()
*/