import { configureStore } from '@reduxjs/toolkit';
import authReducer from './Authentication/AuthSlice';
import breakdownReducer from './Breakdown/BreakdownSlice';

const store = configureStore({
  reducer: {
    auth: authReducer,
    breakdowns: breakdownReducer,
  },
});

export default store;