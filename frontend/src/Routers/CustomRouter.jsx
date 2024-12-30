import {
  createBrowserRouter,
  RouterProvider,
  useLocation,
} from "react-router-dom";
import Home from "../component/Home/Home";
import OrdersList from "../component/Orders/OrdersList";
import Profile from "../component/Profile/Profile";
import Cart from "../component/Cart/Cart";
import { Navbar } from "../component/Navbar/Navbar";
import ProfileUser from "../component/Profile/ProfileUser";
import ProfileBreakdowns from "../component/Profile/ProfileBreakdowns";
import ProfileOrders from "../component/Profile/ProfileOrders";
import ProfileWorkers from "../component/Profile/ProfileWorkers";
import ProfileWorkOrders from "../component/Profile/ProfileWorkOrders";
import BreakdownsList from "../component/Breakdown/BreakdownsList";
import { BreakdownDetails } from "../component/Breakdown/BreakdownDetails";
import { Auth } from "../component/Auth/Auth";

const router = createBrowserRouter([
  { path: "/", element: <Home /> },
  {
    path: "/breakdowns",
    element: <BreakdownsList />,
    children: [{ path: ":id", element: <BreakdownDetails /> }],
  },
  { path: "/orders", element: <OrdersList /> },
  { path: "/profile/me", element: <Profile /> },
  { path: "/cart", element: <Cart /> },
  {
    path: "/profile",
    element: <Profile />,
    children: [
      { path: "", element: <ProfileUser /> },
      { path: "workorders", element: <ProfileWorkOrders /> },
      { path: "breakdowns", element: <ProfileBreakdowns /> },
      { path: "orders", element: <ProfileOrders /> },
      { path: "workers", element: <ProfileWorkers /> },
    ],
  },
  { path: "/account/login", element: <Auth /> },
]);

export const CustomRouter = () => {
  return (
    <>
      <Navbar />
      <RouterProvider router={router} />
    </>
  );
};
