import {
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  LOGOUT,
} from "./actionTypes";

const initialState = {
  user: null,
  loading: false,
  error: null,
  jwt: null,
  success: null,
};

const AuthReducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_REQUEST:
    case LOGIN_REQUEST:
      return { ...state, loading: true, error: null, success: null };
    case REGISTER_SUCCESS:
      return {
        ...state,
        loading: false,
        user: action.payload.user || null,
        jwt: action.payload.jwt || null,
        error: null,
        success: true,
      };
    case LOGIN_SUCCESS:
      return {
        ...state,
        loading: false,
        user: action.payload.user || null,
        jwt: action.payload.jwt || null,
        error: null,
        success: true,
      };
    case REGISTER_FAILURE:
    case LOGIN_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
        success: null,
      };
    case LOGOUT:
      return { ...initialState };
    default:
      return state;
  }
};

export default AuthReducer;