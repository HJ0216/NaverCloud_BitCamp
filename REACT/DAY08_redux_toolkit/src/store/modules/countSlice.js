import React from 'react';

import { createSlice } from '@reduxjs/toolkit';

const initialState = {count: 0};

const countSlice = createSlice({
    name: 'count',
    initialState,
    reducers: {
        increment(state) {state.count += 1},
        decrement(state) {state.count -= 1},
        reset(state) {state.count = 0},
    },

});

export const countActions = countSlice.actions;

export default countSlice;