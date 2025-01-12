import { api } from "../../Config/Api";
import { getUserRequest, getUserSuccess } from "./AuthSlice";

export const getUser = (jwt) => {
  return async (dispatch) => {
    dispatch(getUserRequest());
    try {
      const response = await api.post(`/getUser`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });

      dispatch(getUserSuccess);
      console.log("user profile ", response.data);
    } catch (error) {
        console.log(error);
    }
  };
};