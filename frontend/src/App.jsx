import { CssBaseline, ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./Theme/DarkTheme";
import { CustomRouter } from "./Routers/CustomRouter";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import { store } from "./component/State/store";

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <BrowserRouter>
          <Provider store={store}>
            <CustomRouter />
          </Provider>
        </BrowserRouter>
      </ThemeProvider>
    </div>
  );
}

export default App;
