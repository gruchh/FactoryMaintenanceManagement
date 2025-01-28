import "./App.css";
import { CssBaseline } from "@mui/material";
import { BrowserRouter } from "react-router-dom";
import { CustomRouter } from "./Routers/CustomRouter";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./component/State/Authentication/authActions";

function App() {

  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const auth = useSelector(store => store.auth);

  useEffect(() => {
    console.log("getUser is called with:", auth.jwt || jwt || dispatch);
    dispatch(getUser(auth.jwt || jwt));
  }, [auth.jwt, dispatch, jwt]);

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