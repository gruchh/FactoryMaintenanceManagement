import { configureStore } from '@reduxjs/toolkit';
import authReducer from './Authentication/AuthSlice';
import breakdownReducer from './Breakdown/BreakdownSlice';
import carouselReducer from './Carousel/CarouselSlice';

const store = configureStore({
  reducer: {
    auth: authReducer,
    breakdowns: breakdownReducer,
    carousel: carouselReducer,
  },
});

export default store;