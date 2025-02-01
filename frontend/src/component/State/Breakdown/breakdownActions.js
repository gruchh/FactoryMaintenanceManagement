import { api, API_URL } from "../../Config/Api";
import {
    getBreakdownsRequest,
    getBreakdownsSuccess,
    getBreakdownsFailure,
    getPaginatedBreakdownsRequest,
    getPaginatedBreakdownsSuccess,
    getPaginatedBreakdownsFailure,
    getBreakdownRequest,
    getBreakdownSuccess,
    getBreakdownFailure,
    saveBreakdownRequest,
    saveBreakdownSuccess,
    saveBreakdownFailure,
    updateBreakdownRequest,
    updateBreakdownSuccess,
    updateBreakdownFailure,
    deleteBreakdownRequest,
    deleteBreakdownSuccess,
    deleteBreakdownFailure,
} from "./BreakdownSlice";

export const getAllBreakdowns = (jwt) => async (dispatch) => {
  dispatch(getBreakdownsRequest());
  try {
    const { data: responseData } = await api.get(`${API_URL}/breakdowns`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch(getBreakdownsSuccess(responseData));
  } catch (error) {
    dispatch(getBreakdownsFailure({ message: error.message }));
    console.log(error);
  }
};

export const getPaginatedBreakdowns = (jwt, page, sortDirection) => async (dispatch) => {
  dispatch(getPaginatedBreakdownsRequest());
    try {
        const { data: responseData } = await api.get(`${API_URL}/breakdowns/page`, {
            params: {
                value: page+1,
                sortDirection: sortDirection
            },
            headers: {
                Authorization: `Bearer ${jwt}`,
            },
        });
        dispatch(getPaginatedBreakdownsSuccess(responseData));
    } catch (error) {
        dispatch(getPaginatedBreakdownsFailure({ message: error.message }));
        console.log(error);
    }
};

export const getBreakdown = (id, jwt) => async (dispatch) => {
    dispatch(getBreakdownRequest());
    try {
        const { data: responseData } = await api.get(`${API_URL}/breakdowns/${id}`, {
            headers: {
                Authorization: `Bearer ${jwt}`,
            },
        });
        dispatch(getBreakdownSuccess(responseData));
    } catch (error) {
        dispatch(getBreakdownFailure({ message: error.message }));
        console.log(error);
    }
};

export const saveBreakdown = (breakdown, jwt) => async (dispatch) => {
  dispatch(saveBreakdownRequest());
  try {
    const { data: responseData } = await api.post(
      `${API_URL}/breakdowns`,
      breakdown,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispatch(saveBreakdownSuccess(responseData));
  } catch (error) {
    dispatch(saveBreakdownFailure({ message: error.message }));
    console.log(error);
  }
};

export const updateBreakdown = (breakdown, jwt) => async (dispatch) => {
    dispatch(updateBreakdownRequest());
    try {
        const { data: responseData } = await api.put(
            `${API_URL}/breakdowns`,
            breakdown,
            {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            }
        );
        dispatch(updateBreakdownSuccess(responseData));
    } catch (error) {
        dispatch(updateBreakdownFailure({ message: error.message }));
        console.log(error);
    }
};

export const deleteBreakdown = (id, jwt) => async (dispatch) => {
    dispatch(deleteBreakdownRequest());
    try {
        await api.delete(`${API_URL}/breakdowns/${id}`, {
            headers: {
                Authorization: `Bearer ${jwt}`,
            },
        });
        dispatch(deleteBreakdownSuccess(id));
    } catch (error) {
        dispatch(deleteBreakdownFailure({ message: error.message }));
        console.log(error);
    }
};