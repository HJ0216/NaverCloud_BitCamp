import React, { useMemo } from 'react';

// 함수 선언이 컴포넌트 외부에서 이뤄져도 가능
const getColor = (color) => {
    console.log('getColor');

    switch(color){
        case 'tomato':
            return 'tomato'
        case 'dodgerblue':
            return 'dodgerblue'
        case 'salmon':
            return 'salmon'
        case 'aliceblue':
            return 'aliceblue'
    }
}



const getFood = (food) => {
    console.log('getFood');

    switch(food){
        case 'hamburger':
            return 'Hamburger'
        case 'pork':
            return 'Pork'
        case 'chicken':
            return 'Chicken'
        case 'pizza':
            return 'Pizza'
    }
}



const Test04Sub = ({color, food}) => {

    // const colorInfo = getColor(color);
    // const foodInfo = getFood(food);

    // color 또는 food 선택에 상관없이 get 함수가 실행되어 console에 찍힘
    // ▶ 시스템 부하문제를 해결하기 위해 color → getColor, food → getFood, 원하는 것만 랜더링
    // ▶ useMemo() 사용: 부분적 업데이트 실행

    const colorInfo = useMemo(() => {
        getColor(color)
    }, [color]);

    const foodInfo = useMemo(() => {
        getFood(food)
    }, [food]);


    return (
        <div>
            <h3>Selected Color: {color}</h3>
            <h4>Like Color: {colorInfo}</h4>

            <h3>Selected food: {food}</h3>
            <h4>Like food: {foodInfo}</h4>

        </div>
    );
};

export default Test04Sub;