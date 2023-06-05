import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { countActions } from "../../store/modules/countSlice";

const Count = () => {
  const count = useSelector((state) => state.count.count);
  // state: 상태, color: index.js reducerName, count: variableName
  const dispatch = useDispatch();

  const incrementHandler = () => {
    dispatch(countActions.increment());
  };
  const decrementHandler = () => {
    dispatch(countActions.decrement());
  };
  const resetHandler = () => {
    dispatch(countActions.reset());
  };

  return (
    <div>
      <h1>카운드: {count}</h1>
      <p>
        <button onClick={incrementHandler}>증가</button>
        <button onClick={decrementHandler}>감소</button>
        <button onClick={resetHandler}>초기화</button>
      </p>
    </div>
  );
};

export default Count;
