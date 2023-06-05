import { createSlice } from "@reduxjs/toolkit";

const initialState = { name: "", crying: "" };

const animalSlice = createSlice({
  name: "animal",
  initialState,
  reducers: { // reducer: 액션(애플리케이션에서 어떤 변화가 필요한지 설명) 생성자 역할
    tiger(state) {
        state.name = "호랑이";
        state.crying = "어흥";
    },
    dog(state) {
        state.name = "강아지";
        state.crying = "멍멍";  
    },
    cat(state) {
        state.name = "고양이";
        state.crying = "야옹";
    },
    chick(state) {
        state.name = "병아리";
        state.crying = "삐약";
    },
  },
});

export const animalActions = animalSlice.actions;

export default animalSlice;
