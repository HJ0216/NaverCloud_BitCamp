import React, { useState } from 'react';
import Test04Sub from './Test04Sub';

const Test04 = () => {
    const [color, setColor] = useState('');

    const onColor = (e) => {
        const {value} = e.target;
        setColor(value);
    }


    const [food, setFood] = useState('');

    const onFood = (e) => {
        const {value} = e.target;
        setFood(value);
    }

    return (
        <div style={{margin: 30}}>
            <h2>당신이 좋아하는 색깔은?</h2>
            <div>
                <input type='text' value={color} onChange={onColor} />
                <p>선택: tomato, dodgerblue, salmon, aliceblue</p>
            </div>

            <hr/>


            <h2>당신이 좋아하는 음식은?</h2>
            <div>
                <p>
                    <input type='radio' name='food' value='hamburger' id='hbg' onChange={onFood} />
                    <label for='hbg'>Hamburger</label>
                </p>


                <p>
                    <input type='radio' name='food' value='pork' id='pork' onChange={onFood} />
                    <label for='pork'>pork</label>
                </p>


                <p>
                    <input type='radio' name='food' value='chicken' id='chk' onChange={onFood} />
                    <label for='chk'>Chicken</label>
                </p>


                <p>
                    <input type='radio' name='food' value='pizza' id='pizza' onChange={onFood} />
                    <label for='pizza'>Pizza</label>
                </p>

            </div>

            <hr/>

            <Test04Sub color={color} food={food} />

        </div>
    );
};

export default Test04;