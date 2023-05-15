import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import data from './NavData';
// Component 삽입 대상이 아니면, 파일 이름 소문자로 시작

const NavBar = () => {
    const [isNav, setIsNav] = useState(false);
    const onToggle = () => {
        setIsNav((current) => !current);
    }
    return (
        <div className='navbar'>
            <p className='all-menu' onClick={onToggle}>SIDE BAR</p>
            <nav className={isNav ? 'on' : ''}>
            {/* className에 'on'을 부여할 경우, left=0이 되어 nav가 사라짐 */}
                <ul>
                    {/* 
                        <li><Link to='/'>Home</Link></li>
                        <li><Link to='/about'>About</Link></li>
                        <li><Link to='/ceo'>Ceo</Link></li>
                        <li><Link to='/sub01'>Sub01</Link></li>
                        <li><a href='/about'>aTag Home</a></li>
                    */}

                    { // (item, index) 순서 유지 필수
                        data.map((item, index) => <li key={index}>
                            <Link to={item.path}>{item.title}</Link>
                        </li>)
                    }

                </ul>
                <p className='close' onClick={ () => {setIsNav(false)}}>X</p>
            </nav>

        </div>
    );
};

export default NavBar;