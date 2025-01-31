import "./App.css";
import { CssBaseline } from "@mui/material";
import { BrowserRouter } from "react-router-dom";
import { CustomRouter } from "./Routers/CustomRouter";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getUser } from "./component/State/Authentication/authActions";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
    }
  }, [jwt, dispatch]);

  return (
    <div className="App">
      <CssBaseline />
      <BrowserRouter>
        <CustomRouter />
      </BrowserRouter>
    </div>
  );
}

export default App;
