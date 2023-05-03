import React from 'react';
import Test5Sub from './Test5Sub';

const Test5 = () => {
    return (
        <div>
            <Test5Sub name='홍길동'
                       age={19}
                       addr='서울'
                       tel='010-1234-5678'
                       color='tomato'
                       bgcolor='dodgerblue'
                       done={true} />
            <hr />
            <Test5Sub name='코난'
                      addr='부산'
                      color='salmon'
                      bgcolor='ghostwhite' />
        </div>
    );
};

export default Test5;