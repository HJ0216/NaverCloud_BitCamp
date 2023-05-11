import React from 'react';

import img01 from '../img/cat01.jpg';
import img02 from '../img/cat02.jpg';
import img03 from '../img/cat03.jpg';
import img04 from '../img/cat04.jpg';

const Test09 = () => {
    return (
        <div>
            <img src={img01} style={{width: 200, height: 200}} />
            <img src={img02} style={{width: 200, height: 200}} />
            <img src={img03} style={{width: 200, height: 200}} />
            <img src={img04} style={{width: 200, height: 200}} />
        </div>
    );
};

export default Test09;
