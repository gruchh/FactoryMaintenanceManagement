import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: {
    username: null,
    email: null,
    roles: [],
  },
  loading: false,
  error: null,
  jwt: null,
  success: null,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    registerRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    registerSuccess: (state, action) => {
      state.loading = false;
      state.jwt = action.payload.jwt || null;
      state.error = null;
      state.success = true;
    },
    registerFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload.message;
      state.success = null;
    },
    loginRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    loginSuccess: (state, action) => {
      state.loading = false;
      state.user = {
        ...state.user,
        username: action.payload.username || null,
        email: action.payload.email || null,
        roles: action.payload.roles ? [...action.payload.roles] : [],
      }
      state.jwt = action.payload.jwt || null;
      state.error = null;
      state.success = true;
    },
    loginFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload.message;
      state.success = null;
    },
    getUserRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = null;
    },
    getUserSuccess: (state, action) => {
      state.loading = false;
      state.user = {
        ...state.user,
        username: action.payload.username || null,
        email: action.payload.email || null,
        roles: action.payload.roles ? [...action.payload.roles] : [],
      };
      state.error = null;
      state.success = true;
    },
    getUserFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload.message;
      state.success = null;
    },
    logoutSuccess: (state) => {
      state.user = {
        username: null,
        email: null,
        roles: [],
      };
      state.loading = false;
      state.error = null;
      state.jwt = null;
      state.success = null;
    },
  },
});

export const {
  registerRequest,
  registerSuccess,
  registerFailure,
  loginRequest,
  loginSuccess,
  loginFailure,
  getUserRequest,
  getUserSuccess,
  getUserFailure,
  logoutSuccess,
} = authSlice.actions;

export default authSlice.reducer;
