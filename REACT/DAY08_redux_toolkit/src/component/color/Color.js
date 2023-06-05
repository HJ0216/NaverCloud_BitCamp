import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { colorActions } from "../../store/modules/colorSlice";

const Color = () => {
  const color = useSelector((state) => state.color.color);
  // 데이터 추출: state / color(index.js-reducer 선언 이름) / color(변수)
  const dispatch = useDispatch();

  const redHandler = () => {
    dispatch(colorActions.red());
  };
  const greenHandler = () => {
    dispatch(colorActions.green());
  };
  const blueHandler = () => {
    dispatch(colorActions.blue());
  };
  const tomatoHandler = () => {
    dispatch(colorActions.tomato());
  };

  return (
    <div>
      <h1 style={{ color: color }}>컬러: {color}</h1>
      <p>
        <button onClick={redHandler}>RED</button>
        <button onClick={greenHandler}>GREEN</button>
        <button onClick={blueHandler}>BLUE</button>
        <button onClick={tomatoHandler}>TOMATO</button>
      </p>
    </div>
  );
};

export default Color;
