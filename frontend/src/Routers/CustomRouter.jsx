import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "../component/Home/Home";
import BreakdownDetails from "../component/Breakdown/BreakdownDetails";
import OrdersList from "../component/Orders/OrdersList";
import Profile from "../component/Profile/Profile";
import Cart from "../component/Cart/Cart";
import { Navbar } from "../component/Navbar/Navbar";
import BreakdownsList from "../component/Breakdown/BreakdownsList";
import UserProfile from "../component/Profile/UserProfile";
import WorkOrders from "../component/Profile/WorkOrders";
import ProfileBreakdowns from "../component/Profile/ProfileBreakdowns";
import ProfileOrders from "../component/Profile/ProfileOrders";
import ProfileWorkers from "../component/Profile/ProfileWorkers";

const router = createBrowserRouter([
  { path: "/", element: <Home /> },
  { path: "/breakdowns", element: <BreakdownDetails /> },
  { path: "/orders", element: <OrdersList /> },
  { path: "/profile/me", element: <Profile /> },
  { path: "/cart", element: <Cart /> },
  {
    path: "/profile",
    element: <Profile />,
    children: [
      { path: "", element: <UserProfile /> },
      { path: "workorders", element: <WorkOrders /> },
      { path: "breakdowns", element: <ProfileBreakdowns /> },
      { path: "orders", element: <ProfileOrders /> },
      { path: "workers", element: <ProfileWorkers /> },
    ],
  },
]);

export const CustomRouter = () => {
  return (
    <>
      <Navbar />
      <RouterProvider router={router} />
    </>
  );
};
