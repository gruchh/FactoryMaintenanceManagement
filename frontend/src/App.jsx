import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { Navbar } from "./component/Navbar/Navbar";
import Home from "./component/Home/Home";
import Profile from "./component/Profile/Profile";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import BreakdownDetails from "./component/Breakdown/BreakdownDetails";
import OrdersList from "./component/Orders/OrdersList";
import Cart from "./component/Cart/Cart";

let router = createBrowserRouter([
  {
    path: "/",
    Component: Home,
  },
  {
    path: "/breakdowns",
    Component: BreakdownDetails,
  },
  {
    path: "/orders",
    Component: OrdersList,
  },
  {
    path: "/profile/me",
    Component: Profile,
  },
  {
    path: "/cart",
    Component: Cart,
  },
]);

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <Navbar />
        <RouterProvider router={router} />
      </ThemeProvider>
    </div>
    
  );
}

export default App;
