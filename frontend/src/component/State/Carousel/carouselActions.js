import { api } from "../../Config/Api";
import { fetchCarouselItemsFailure, fetchCarouselItemsRequest, fetchCarouselItemsSuccess } from "./CarouselSlice";

export const fetchCarouselItems = () => async (dispatch) => {
    dispatch(fetchCarouselItemsRequest());
    try {
      const response = await api.get("/carousel-item/visible");
      dispatch(fetchCarouselItemsSuccess(response.data));
    } catch (error) {
      dispatch(fetchCarouselItemsFailure({ message: error.message }));
    }
  };