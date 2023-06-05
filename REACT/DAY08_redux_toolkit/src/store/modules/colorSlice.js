import React from 'react';

import { createSlice } from '@reduxjs/toolkit';

const initialState = {color: 'hotpink'};

const colorSlice = createSlice({
    name: 'color',
    initialState,
    reducers: {
        red(state) {
            state.color = "RED"
        },
        green(state) {
            state.color = "GREEN"
        },
        blue(state) {
            state.color = "BLUE"
        },
        tomato(state) {
            state.color = "TOMATO"
        },
    },    
});

export const colorActions = colorSlice.actions;

export default colorSlice;
