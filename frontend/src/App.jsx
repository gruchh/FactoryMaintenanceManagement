import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { Navbar } from "./component/Navbar/Navbar";
import Home from "./component/Home/Home";
import Profile from "./component/Profile/Profile";
import { BrowserRouter, Route, Router, Routes } from "react-router-dom";
import BreakdownDetails from "./component/Breakdown/BreakdownDetails";
import OrdersList from "./component/Orders/OrdersList";
import Cart from "./component/Cart/Cart";
// import Profile from "./component/Profile/Profile";

function App() {
  return (
    <>
      <div className="App">
        <ThemeProvider theme={darkTheme}>
          <CssBaseline />
          <BrowserRouter>
            <Navbar />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/breakdowns" element={<BreakdownDetails />} />
              <Route path="/orders" element={<OrdersList />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/profile" element={<Profile />} />
            </Routes>
          </BrowserRouter>
        </ThemeProvider>
      </div>
    </>
  );
}

export default App;
