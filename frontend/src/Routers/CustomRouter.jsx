import { Route, Routes } from "react-router-dom";
import { Auth } from "../component/Auth/Auth";
import { BreakdownDetails } from "../component/Breakdown/BreakdownDetails";
import BreakdownsList from "../component/Breakdown/BreakdownsList";
import Cart from "../component/Cart/Cart";
import Home from "../component/Home/Home";
import { Navbar } from "../component/Navbar/Navbar";
import OrdersList from "../component/Orders/OrdersList";
import Profile from "../component/Profile/Profile";
import ProfileBreakdowns from "../component/Profile/ProfileBreakdowns";
import ProfileOrders from "../component/Profile/ProfileOrders";
import ProfileUser from "../component/Profile/ProfileUser";
import ProfileWorkers from "../component/Profile/ProfileWorkers";
import ProfileWorkOrders from "../component/Profile/ProfileWorkOrders";

export const CustomRouter = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/breakdowns" element={<BreakdownsList />}>
          <Route path=":id" element={<BreakdownDetails />} />
        </Route>
        <Route path="/orders" element={<OrdersList />} />
        <Route path="/profile/me" element={<Profile />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/profile" element={<Profile />}>
          <Route path="" element={<ProfileUser />} />
          <Route path="workorders" element={<ProfileWorkOrders />} />
          <Route path="breakdowns" element={<ProfileBreakdowns />} />
          <Route path="orders" element={<ProfileOrders />} />
          <Route path="workers" element={<ProfileWorkers />} />
        </Route>
        <Route path="/account/login" element={<Auth />} />
      </Routes>
      <Auth />
    </>
  );
};
