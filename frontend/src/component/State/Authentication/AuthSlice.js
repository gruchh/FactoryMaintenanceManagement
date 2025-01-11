import { createSlice } from '@reduxjs/toolkit'

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    user: null,
    loading: false,
    error: null,
    jwt: null,
    success: null,
  },
  reducers: {
    registerRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    loginRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    registerSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload.user || null;
      state.jwt = action.payload.jwt || null;
      state.error = null;
      state.success = true;
    },
    loginSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload.user || null;
      state.jwt = action.payload.jwt || null;
      state.error = null;
      state.success = true;
    },
    registerFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.success = null;
    },
    loginFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.success = null;
    },
    logout: (state) => {
      state.user = null;
      state.loading = false;
      state.error = null;
      state.jwt = null;
      state.success = null;
    },
    getUserRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    getUserSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload.user || null;
      state.error = null;
      state.success = true;
    }
  },
})

export const { 
  registerRequest, 
  registerSuccess, 
  registerFailure, 
  loginRequest, 
  loginSuccess, 
  loginFailure, 
  logout, 
  getUserRequest,
  getUserSuccess
} = authSlice.actions;

export default authSlice.reducer;
