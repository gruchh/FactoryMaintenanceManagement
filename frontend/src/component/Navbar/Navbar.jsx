import { ShoppingCart } from "@mui/icons-material";
import PersonIcon from "@mui/icons-material/Person";
import YoutubeSearchedForIcon from "@mui/icons-material/YoutubeSearchedFor";
import { Avatar, Badge, Box, IconButton } from "@mui/material";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

export const Navbar = () => {
  const auth= useSelector((store) => store.auth);
  const navigate = useNavigate();
  const handleLogin = () => {
    navigate("/account/login");
  };
  const handleAvatarClick = () => {
    navigate("/profile");
  };
  const handleLogoClick = () => {
    navigate("/");
  }
  const isLoggedIn = auth.user.username;

  return (
    <Box className="px-5 top-0 z-50 py-[.8rem] bg-[#543671] lg:px-20 lg:py-3 flex justify-between">
      <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
        <li onClick={() => handleLogoClick()}className="logo font-semibold text-gray-500 text-2xl list-none">
          Maintenance management
        </li>
      </div>
      <div className="flex items-center space-x-2 lg:space-x-10">
        <div>
          <IconButton aria-label="search">
            <YoutubeSearchedForIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>
        <div>
          {isLoggedIn ? (
            <Avatar
              onClick={() => handleAvatarClick()}
              sx={{ bgColor: "white", color: "pink.A400" }}
            >
              {isLoggedIn.toUpperCase().charAt(0)}
            </Avatar>
          ) : (
            <IconButton onClick={() => handleLogin()} aria-label="login">
              <PersonIcon />
            </IconButton>
          )}
        </div>
        <div>
          <IconButton aria-label="shopping cart">
            <Badge color="primary" badgeContent="3">
              <ShoppingCart sx={{ fontSize: "1.5rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </Box>
  );
};
