import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { Navbar } from "./component/navbar/Navbar";
import { darkTheme } from "./Theme/DarkTheme";

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
      <CssBaseline/>
      <Navbar/>
      </ThemeProvider>
    </div>
  );
}

export default App;
