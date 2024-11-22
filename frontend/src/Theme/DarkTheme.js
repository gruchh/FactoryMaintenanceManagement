import { createTheme } from "@mui/material/styles";

export const darkTheme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#0052cc",
    },
    secondary: {
      main: "#edf2ff",
    },
    black: {
      main: "#242B2E",
    },
    background: {
        main: "#0000000",
        default: "#0D0D0D",
        paper: "#0D0D0D"
    },
    textcolor: {
        main: "111111"
    }
  },
});