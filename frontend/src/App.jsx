import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { CustomRouter } from "./Routers/CustomRouter";

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <CustomRouter />
      </ThemeProvider>
    </div>
    
  );
}

export default App;
