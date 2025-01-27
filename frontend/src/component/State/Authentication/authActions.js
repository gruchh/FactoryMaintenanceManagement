import { api, API_URL } from "../../Config/Api";
import { getUserFailure, getUserRequest, getUserSuccess } from "./AuthSlice";

export const registerUser = (data) => async (dispatch) => {
  console.log(data);
  dispatch(getUserRequest());
  try {
    const { data: responseData } = await api.post(
      `${API_URL}/register`,
      data.userData
    );
    console.log(responseData);
  } catch (error) {
    console.log(error);
  }
};

export const getUser = (jwt) => {
  return async (dispatch) => {
    dispatch(getUserRequest());

    if (!jwt) {
      console.error("JWT is null. Returning user with null values.");
      dispatch(getUserSuccess({ user: null }));
      return;
    }

    try {
      const res = await api.get(`${API_URL}/getUser`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch(getUserSuccess({ user: res.data }));
    } catch (error) {
      console.error("Error fetching user:", error);
      dispatch(getUserFailure({ message: error.message }));
    }
  };
};
