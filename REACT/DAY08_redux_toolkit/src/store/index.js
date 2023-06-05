import { configureStore } from "@reduxjs/toolkit";
import animalSlice from "./modules/animalSlice";
import colorSlice from "./modules/colorSlice";
import countSlice from "./modules/countSlice";

const store = configureStore({
  reducer: { animal: animalSlice.reducer, color: colorSlice.reducer, count: countSlice.reducer },
});

export default store;
