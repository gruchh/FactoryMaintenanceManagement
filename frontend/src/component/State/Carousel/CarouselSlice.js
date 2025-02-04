import { createSlice } from "@reduxjs/toolkit";

// const initialCarouselItem = {
//     id: null,
//     imageUrl: null,
//     title: null,
//     link: null,
//     isVisible: false,
// };

const initialState = {
    carouselItems: [],
    loading: false,
    error: null,
    success: null,
};

export const carouselSlice = createSlice({
    name: "carousel",
    initialState,
    reducers: {
        fetchCarouselItemsRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        fetchCarouselItemsSuccess: (state, action) => {
            state.loading = false;
            state.carouselItems = action.payload;
            state.error = null;
            state.success = true;
        },
        fetchCarouselItemsFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        }
    },
});

export const {
    fetchCarouselItemsRequest,
    fetchCarouselItemsSuccess,
    fetchCarouselItemsFailure
} = carouselSlice.actions;

export default carouselSlice.reducer;