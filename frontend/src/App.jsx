import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { CustomRouter } from "./Routers/CustomRouter";
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <BrowserRouter>
          <CustomRouter />
        </BrowserRouter>
      </ThemeProvider>
    </div>
  );
}

export default App;
