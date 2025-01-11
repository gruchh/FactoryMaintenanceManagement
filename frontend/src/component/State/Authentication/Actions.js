import axios from "axios";
import {
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  GET_USER_FAILURE,
  LOGOUT_REQUEST,
  LOGOUT_SUCCESS,
} from "./ActionTypes";
import { api, API_URL } from "../../Config/Api";

export const registerUser = (credentials) => {
  return async (dispatch) => {
    dispatch({ type: REGISTER_REQUEST });
    try {
      const response = await axios.post(`${API_URL}/register`, credentials);

      if (response.data && response.data.jwt) {
        localStorage.setItem("jwt", response.data.jwt);
      }

      dispatch({ type: REGISTER_SUCCESS, payload: response.data });
      console.log("register ", response.data);
    } catch (error) {
      dispatch({
        type: REGISTER_FAILURE,
        payload: error.response ? error.response.data : error.message,
      });
    }
  };
};

export const loginUser = (credentials) => {
  return async (dispatch) => {
    dispatch({ type: LOGIN_REQUEST });
    try {
      const response = await axios.post(`${API_URL}/login`, credentials);

      if (response.data && response.data.jwt) {
        localStorage.setItem("jwt", response.data.jwt);
      }

      dispatch({ type: LOGIN_SUCCESS, payload: response.data });
      console.log("login ", response.data);
    } catch (error) {
      dispatch({
        type: LOGIN_FAILURE,
        payload: error.response ? error.response.data : error.message,
      });
    }
  };
};

export const getUser = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: GET_USER_REQUEST });
    try {
      const response = await api.post(`/login`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });

      dispatch({ type: GET_USER_SUCCESS, payload: response.data });
      console.log("user profile ", response.data);
    } catch (error) {
      dispatch({
        type: GET_USER_FAILURE,
        payload: error.response ? error.response.data : error.message,
      });
    }
  };
};

export const logout = () => {
  return async (dispatch) => {
    dispatch({ type: LOGOUT_REQUEST });
    try {
      dispatch({ type: LOGOUT_SUCCESS });
      localStorage.clear();
      console.log("logout success");
    } catch (error) {
      console.log("error ", error);
    }
  };
};
