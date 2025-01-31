import { Box, Modal } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import { RegisterForm } from "./RegisterForm";
import { LoginForm } from "./LoginForm";

export const Auth = () => {
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };
  const navigate = useNavigate();
  const location = useLocation();
  const isOpen =
    location.pathname === "/account/login" ||
    location.pathname === "/account/register";
  const handleClose = () => {
    navigate(-1);
  };
  const renderForm = () => {
    switch (location.pathname) {
      case "/account/login":
        return <LoginForm />;
      case "/account/register":
        return <RegisterForm />;
      default:
        return null;
    }
  };

  return (
    <Modal
      open={isOpen}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>{renderForm()}</Box>
    </Modal>
  );
};

export default Auth;
