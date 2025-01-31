import { api, API_URL } from "../../Config/Api";
import {
  getUserFailure,
  getUserRequest,
  getUserSuccess,
  loginFailure,
  loginRequest,
  loginSuccess,
  logoutSuccess,
  registerFailure,
  registerRequest,
  registerSuccess,
} from "./AuthSlice";

export const registerUser = (data) => async (dispatch) => {
  dispatch(registerRequest());
  try {
    const { data: responseData } = await api.post(
      `${API_URL}/register`,
      data.userData
    );
    if (responseData.accessToken) {
      localStorage.setItem("jwt", responseData.accessToken);
      dispatch(
        registerSuccess({
          jwt: responseData.accessToken,
        })
      );
    }
  } catch (error) {
    dispatch(registerFailure({ message: error.message }));
    console.log(error);
  }
};

export const loginUser = (data) => async (dispatch) => {
  dispatch(loginRequest());
  try {
    const { data: responseData } = await api.post(
      `${API_URL}/login`,
      data.userData
    );
    if (responseData.accessToken) {
      localStorage.setItem("jwt", responseData.accessToken);
      dispatch(
        loginSuccess({
          username: responseData.user.username,
          email: responseData.user.email,
          roles: responseData.user.roles || [],
          jwt: responseData.accessToken,
        })
      );
    }
  } catch (error) {
    dispatch(loginFailure({ message: error.message }));
    console.log(error);
  }
};

export const logoutUser = () => (dispatch)=> {
  localStorage.removeItem("jwt");
  dispatch(logoutSuccess());
};

export const getUser = (jwt) => {
  return async (dispatch) => {
    dispatch(getUserRequest());
    if (!jwt) {
      console.error("JWT is null. Returning user with null values.");
      dispatch(getUserSuccess({ username: null, email: null, roles: [] }));
      return;
    }
    try {
      const res = await api.get(`${API_URL}/getUser`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch(
        getUserSuccess({
          username: res.data.username,
          email: res.data.email,
          roles: res.data.roles || [],
        })
      );
    } catch (error) {
      console.error("Error fetching user:", error);
      dispatch(getUserFailure({ message: error.message }));
    }
  };
};