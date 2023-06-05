import React from 'react';
import {useDispatch, useSelector} from 'react-redux'
import { animalActions } from '../../store/modules/animalSlice';

const Animal = () => {
    const name = useSelector(state => state.animal.name); // 데이터 추출
    const crying = useSelector(state => state.animal.crying);

    const dispatch = useDispatch(); // action 발생

    const tigerHandler = () => {
        dispatch(animalActions.tiger());
    };
    const dogHandler = () => {
        dispatch(animalActions.dog());
    };
    const catHandler = () => {
        dispatch(animalActions.cat());
    };
    const chickHandler = () => {
        dispatch(animalActions.chick());
    };

    return (
        <div>
            <h1>동물의 울음소리</h1>
            <h1>{name} : {crying}</h1>
            <p>
                <button onClick={tigerHandler}>호랑이</button>
                <button onClick={dogHandler}>강아지</button>
                <button onClick={catHandler}>고양이</button>
                <button onClick={chickHandler}>병아리</button>
            </p>
        </div>
    );
};

export default Animal;