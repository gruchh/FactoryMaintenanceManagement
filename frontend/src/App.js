import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { Navbar } from "./component/Navbar/Navbar";
import Home from "./component/Home/Home";
import BreakdownDetails from "./component/Breakdown/BreakdownDetails";
import OrdersList from "./component/Orders/OrdersList";

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <Navbar />
        {/* <Home/>  */}
        <BreakdownDetails/>
        {/* <OrdersList/> */}
      </ThemeProvider>
    </div>
  );
}

export default App;
